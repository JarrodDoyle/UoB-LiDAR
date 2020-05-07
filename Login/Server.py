#!/usr/bin/env python3
from flask import Flask, request, Response
from flask_cors import CORS
from DatabaseHandler import *
import json
import Util
app = Flask(__name__)
CORS(app)

def genSuccessResponse(datatype, data):
    return Response(json.dumps({
        "status": "Success",
        "type": datatype,
        "data": data,
    }), status=200, mimetype='application/json', headers={
        "Content-Type": "application/json",
    })

def genErrorResponse(message):
    return Response(json.dumps({
        "status": "Error",
        "error_msg": message,
    }), status=500, mimetype='application/json', headers={
        "Content-Type": "application/json",
    })

notJsonError = genErrorResponse("Request payload must be JSON")

@app.route('/login', methods=['POST'])
def login():
    if not request.is_json:
        return notJsonError
    data = request.get_json()
    account = getAccount(data['email'])

    closeDBCon()

    if not account:
        return genErrorResponse("Invalid username or password")

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
