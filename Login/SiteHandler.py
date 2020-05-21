#!/usr/bin/env python3
from flask import Blueprint, request, g
from ResponseFactory import *
from DatabaseHandler import getLiDARS
import requests

siteBlueprint = Blueprint('Sites', __name__)

def beforeRequest():
    g.type = request.url_rule.rule.split("/")[1]

siteBlueprint.before_request(beforeRequest)

@siteBlueprint.route('/add', methods=['POST'])
def add():
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented")

@siteBlueprint.route('/get', methods=['GET'])
def get():
    token = request.args.get('token')
    if token == None:
        return genErrorResponse("Token missing", status=401)

    if g.type == "lidars":
        return genSuccessResponse("lidars", getLiDARS(token))

    return genErrorResponse("Not Implemented")

@siteBlueprint.route('/getKPIs', methods=['GET'])
def getKPIs():
    r = requests.get("http://localhost:8080/database/EXBUOY/kpis")
    return genSuccessResponse(f'{g.type} get', r.json())

if __name__ == "__main__":
    print ("Not to be run directly")
    exit(1)
