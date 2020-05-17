#!/usr/bin/env python3
from flask import Blueprint, request, g
from ResponseFactory import *

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
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented")

@siteBlueprint.route('/getKPIs', methods=['GET'])
def getKPIs():
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented")


if __name__ == "__main__":
    print ("Not to be run directly")
    exit(1)
