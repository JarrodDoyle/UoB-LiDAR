#!/usr/bin/env python3
from flask import Blueprint, request
from ResponseFactory import *

siteBlueprint = Blueprint('Sites', __name__)

@siteBlueprint.route('/addSite', methods=['POST'])
def addSites():
    if not request.is_json:
        return notJsonError
    return genErrorResponse("Not Implemented")


@siteBlueprint.route('/getSites', methods=['GET'])
def getSites():
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
