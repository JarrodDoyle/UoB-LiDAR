#!/usr/bin/env python3
from flask import Blueprint, request
from DatabaseHandler import getAccount, addAccount, addLoginMeta, closeDBCon
from ResponseFactory import *
import Util

accountBlueprint = Blueprint('Accounts', __name__)

@accountBlueprint.route('/login', methods=['POST'])
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

@accountBlueprint.route('/register', methods=['POST'])
def register():
    if not request.is_json:
        return notJsonError
    data = request.get_json()
    if getAccount(data['email']):
        closeDBCon()
        return genErrorResponse("Account already exists")

    response = None
    salt = Util.genRandomString(64)
    if addAccount(data['email'], Util.hashPassword(data['password'], salt), salt):
        response = genSuccessResponse("register", {})
    else:
        response = genErrorResponse("Internal Server Error")
    closeDBCon()
    return response

@accountBlueprint.route('/forgot', methods=['POST'])
def forgot():
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented", status=501)

if __name__ == "__main__":
    print("Not to be run directly")
    exit(1)
