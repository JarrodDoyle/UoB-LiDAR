#!/usr/bin/env python3
from flask import Flask, request
from DatabaseHandler import *
import scrypt
import base64
import os
app = Flask(__name__)

def hashPassword(password):
    return base64.b64encode(scrypt.hash('password', os.environ['LOGIN_SERVER_SALT'])).decode()

def notJsonError():
    return {
        "status": "Error",
        "error_msg": "Request payload must be JSON",
    }

@app.route('/login', methods=['POST'])
def login():
    if not request.is_json:
        return notJsonError()
    data = request.get_json()
    account = getAccount(data['email'])

    closeDBCon()
    print(account["pw_hash"], hashPassword(data['password']))
    if not account or account["pw_hash"] != hashPassword(data['password']):
        return {
            "status": "Error",
            "error_msg": "Invalid username or password"
        }
    return {
        "status": "Success",
        "master_key": account["token"],
    }

@app.route('/register', methods=['POST'])
def register():
    if not request.is_json:
        return notJsonError()
    data = request.get_json()
    response = None
    if getAccount(data['email']):
        response = {
            "status": "Error",
            "error_msg": "Account already exists",
        }
    if addAccount(data['email'], hashPassword(data['password'])): 
        response = {
            "status": "Success",
        }
    else:
        response = {
            "status": "Error",
            "error_msg": "Internal Server Error",
        }
    closeDBCon()
    return response

@app.route('/forgot', methods=['POST'])
def forgot():
    return 'Forgot';

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=6000)
