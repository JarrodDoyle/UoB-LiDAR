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

def getCursor():
    return getDBCon().cursor()

def getAccount(email):
    cursor = getCursor()
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
    cursor   = getCursor()
    now      = str(datetime.datetime.now())
    ip       = request.remote_addr
    platform = toUnknownIfNone(request.user_agent.platform)
    name     = toUnknownIfNone(request.user_agent.browser)
    version  = toUnknownIfNone(request.user_agent.version)
    language = toUnknownIfNone(request.user_agent.language)

    cursor.execute("INSERT INTO `previous_logons` (user_id, occurred, ip, platform, browser_name, browser_version, language) VALUES (%s, %s, %s, %s, %s, %s, %s);", (user_id, now, ip, platform, name, version, language,))
    g.db.commit()

def genNewToken():
    cursor = getCursor()
    for i in range(0,10):
        token = Util.genRandomString(64)
        cursor.execute("SELECT token FROM tokens WHERE token=%s;", (token,))
        exists = cursor.fetchone()
        if not exists:
            return token
    return False

def addAccount(email, pw_hash, salt):
    try:
        cursor = getCursor()
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
    cursor = getCursor()
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

def isTokenMaster(token):
    cursor = getCursor()
    cursor.execute("\
        SELECT master\
        FROM tokens\
        WHERE token = %s\
    ", (token,))
    ans = cursor.fetchone()
    return bool(ans[0])

def getUserOrganisationDetails(token):
    cursor = getCursor()
    cursor.execute("\
        SELECT organisation.organisation_id, organisation.name, account_organisation_perm.*\
        FROM organisation\
        INNER JOIN accounts ON organisation.organisation_id = accounts.organisation_id\
        INNER JOIN account_organisation_perm ON account_organisation_perm.id = accounts.organisation_perm\
        INNER JOIN tokens ON tokens.user_id = accounts.user_id\
        WHERE tokens.token = %s\
    ", (token,))
    org = cursor.fetchone()
    return {
        "org_id"                : org[0],
        "org_name"              : org[1],
        "org_user_perm_level"   : org[2],
        "org_user_add_user"     : org[3],
        "org_user_add_site"     : org[4],
        "org_user_add_lidar"    : org[5],
        "org_user_change_perms" : org[6],
        "org_grant_site_access" : org[7],
    }

def getTeamMembersDB(token):
    cursor = getCursor()
    cursor.execute("\
        SELECT accounts.user_id, accounts.email, accounts.organisation_perm,\
            'site', site.site_id, site.name, token_perms_sites.read, token_perms_sites.write\
        FROM accounts\
        INNER JOIN tokens ON accounts.user_id = tokens.user_id\
        INNER JOIN token_perms_sites ON token_perms_sites.token = tokens.token\
        INNER JOIN site ON site.site_id = token_perms_sites.site_id\
        WHERE accounts.organisation_id = (\
            SELECT accounts.organisation_id\
            FROM accounts\
            INNER JOIN tokens ON accounts.user_id = tokens.user_id\
            WHERE tokens.token = %s\
        )\
        UNION ALL\
        SELECT accounts.user_id, accounts.email, accounts.organisation_perm,\
            'lidar', lidar.lidar_id, lidar.name, token_perms_lidars.read, token_perms_lidars.write\
        FROM accounts\
        INNER JOIN tokens ON accounts.user_id = tokens.user_id\
        INNER JOIN token_perms_lidars ON token_perms_lidars.token = tokens.token\
        INNER JOIN lidar ON lidar.lidar_id = token_perms_lidars.lidar_id\
        WHERE accounts.organisation_id = 1\
    ", (token,))
    members = cursor.fetchall()
    res = []
    for x in members:
        if len(res) != 0 and res[-1]["userId"] == x[0]:
            res[-1]["sites" if x[3] == "site" else "lidars"].append({
                "id": x[4],
                "name": x[5],
                "read": bool(x[6]),
                "write": bool(x[7]),
            })
        else:
            res.append({
                "userId": x[0],
                "email": x[1],
                "perms": x[2],
                "sites": [{
                    "id": x[4],
                    "name": x[5],
                    "read": bool(x[6]),
                    "write": bool(x[7]),
                }] if x[3] == "site" else [],
                "lidars": [{
                    "id": x[4],
                    "name": x[5],
                    "read": bool(x[6]),
                    "write": bool(x[7]),
                }] if x[3] == "lidar" else [],
            })
    return res

if __name__ == "__main__":
    print("Not server file... Run Server.py")
    exit(1)
