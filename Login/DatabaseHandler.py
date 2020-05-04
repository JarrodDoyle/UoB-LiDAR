#!/usr/bin/env python3
from flask import g, request
import mysql.connector as mariadb
import datetime
import traceback
import Util

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
    cursor.execute("SELECT accounts.user_id, pw_hash, salt, token FROM accounts RIGHT JOIN tokens ON tokens.user_id = accounts.user_id WHERE email = %s AND tokens.master = 1;", (email,))
    account = cursor.fetchone()
    if account == None:
        return False
    return {
        "user_id": account[0],
        "pw_hash": account[1],
        "salt":    account[2],
        "token":   account[3],
    }

def addLoginMeta(user_id):
    def toUnknownIfNone(var):
        return var if var != None else "Unknown"
    cursor   = getDBCon().cursor()
    now      = str(datetime.datetime.now())
    ip       = request.remote_addr
    platform = toUnknownIfNone(request.user_agent.platform)
    name     = toUnknownIfNone(request.user_agent.browser)
    version  = toUnknownIfNone(request.user_agent.version)
    language = toUnknownIfNone(request.user_agent.language)

    print(user_id, now, ip, platform, name, version, language)
    cursor.execute("INSERT INTO `previous_logons` (user_id, occurred, ip, platform, browser_name, browser_version, language) VALUES (%s, %s, %s, %s, %s, %s, %s);", (user_id, now, ip, platform, name, version, language,))
    g.db.commit()

def genNewToken():
    cursor = getDBCon().cursor()
    for i in range(0,10):
        token = Util.genRandomString(64)
        cursor.execute("SELECT token FROM tokens WHERE token=%s;", (token,)) 
        exists = cursor.fetchone()
        if not exists:
            return token
    return False

def addAccount(email, pw_hash, salt):
    try:
        cursor = getDBCon().cursor()
        token = genNewToken()
        if not token:
            return False
        cursor.execute("INSERT INTO `accounts` (email, pw_hash, salt) VALUES (%s, %s, %s);", (email, pw_hash, salt,))
        cursor.execute("INSERT INTO `tokens` (token, user_id, master) VALUES (%s, LAST_INSERT_ID(), 1);", (token,))
        g.db.commit()
        return True
    except Exception as e:
        print(str(e))
        return False

def getLoginLogs(user_id):
    cursor = getDBCon()
    cursor.execute("SELECT occurred, ip, platform, browser_name, browser_version, language\
            FROM `time_stamps` WHERE user_id=%d LIMIT 20;", (user_id,))
    logons = cursor.fetchall()
    logons = list(map(lambda x: {
        "occurred"       : x[0],
        "ip"             : x[1],
        "platform"       : x[2],
        "browser_name"   : x[3],
        "browser_version": x[4],
        "language"       : x[5],
        }, logons))

if __name__ == "__main__":
    print("Not server file... Run Server.py")
    exit(1)
