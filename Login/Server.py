#!/usr/bin/env python3
from flask import Flask, request
from DatabaseHandler import *
import Util
app = Flask(__name__)

def genSuccessResponse(datatype, data):
    return {
        "status": "Success",
        "type": datatype,
        "data": data,
    }

def genErrorResponse(message):
    return {
        "status": "Error",
        "error_msg": message,
    }

notJsonError = genErrorResponse("Request payload must be JSON")

@app.route('/login', methods=['POST'])
def login():
    if not request.is_json:
        return notJsonError
    data = request.get_json()
    account = getAccount(data['email'])

    closeDBCon()
    guessedPass = Util.hashPassword(data['password'], account["salt"])
    if not account or account["pw_hash"] != guessedPass:
        return genErrorResponse("Invalid username or password")

    addLoginMeta(account["user_id"])
    return genSuccessResponse("login", {"master_key": account["token"],})

@app.route('/register', methods=['POST'])
def register():
    if not request.is_json:
        return notJsonError
    data = request.get_json()
    response = None
    if getAccount(data['email']):
        response = genErrorResponse("Account already exists")
    salt = Util.genRandomString(64)
    if addAccount(data['email'], Util.hashPassword(data['password'], salt), salt): 
        response = genSuccessResponse("register", {})
    else:
        response = genErrorResponse("Internal Server Error")
        closeDBCon()
    return response

@app.route('/forgot', methods=['POST'])
def forgot():
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented")

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=6000)
