#!/usr/bin/env python3
from flask import g
from functools import reduce
import mysql.connector as mariadb
import datetime
import traceback
import random

def getDBCon():
    if 'db' not in g:
        g.db = mariadb.connect(user='aaa', password='bbb', database='Account')
    return g.db

def closeDBCon():
    db = g.pop('db', None)
    if db is not None:
        db.close()

def getAccount(email):
    cursor = getDBCon().cursor()
    cursor.execute("SELECT user_id, pw_hash, token FROM accounts RIGHT JOIN tokens ON tokens.user_id = accounts.user_id WHERE email = %s AND tokens.master = 1;", (email,))
    account = cursor.fetchone()
    if account == None:
        return False
    return {
        "user_id": account[0],
        "pw_hash": account[1],
        "token":   account[2],
    }

def addLoginMeta(user_id, ip, user_agent):
    cursor = getDBCon().cursor()
    now = str(datetime.datetime.now())
    cursor.execute("INSERT INTO `previous_logons` (occurred, user_id, ip, user_agent) VALUES (%d, %s, %s, %s);", (user_id, now, ip, user_agent))
    g.db.commit()


def genNewToken():
    cursor = getDBCon().cursor()
    chars = ['1','2','3','4','5','6','7','8','9','0',
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    ]
    for i in range(0,10):
        token = str(reduce(lambda ans, x: ans + x, [random.choice(chars) for x in range(0,64)]))
        cursor.execute("SELECT token FROM tokens WHERE token=%s;", (token,)) 
        exists = cursor.fetchone()
        if not exists:
            return token
    return False

def addAccount(email, pw_hash):
    try:
        cursor = getDBCon().cursor()
        token = genNewToken()
        if not token:
            return False
        cursor.execute("INSERT INTO `accounts` (email, pw_hash) VALUES (%s, %s);", (email, pw_hash,))
        cursor.execute("INSERT INTO `tokens` (token, user_id, master) VALUES (%s, LAST_INSERT_ID(), 1);", (token,))
        g.db.commit()
        return True
    except Exception as e:
        print(str(e))
        return False

def getLoginLogs(user_id):
    cursor = getDBCon()
    cursor.execute("SELECT * FROM `time_stamps` WHERE user_id=%d;", (user_id,))

if __name__ == "__main__":
    print("Not server file... Run Server.py")
