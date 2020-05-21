#!/usr/bin/env python3
from flask import Blueprint, request
from DatabaseHandler import isTokenMaster, getUserOrganisationDetails, getTeamMembersDB, closeDBCon
from ResponseFactory import *

organisationBlueprint = Blueprint('Organisation', __name__)

@organisationBlueprint.route('/getUserOrganisation', methods=['GET'])
def getUserOrganisation():
    token = request.args.get('token')

    if token == None:
        return genErrorResponse("Token missing", status=401)

    if not isTokenMaster(token):
        closeDBCon()
        return genErrorResponse("Not master token", status=403)

    res = getUserOrganisationDetails(token)
    closeDBCon()
    return genSuccessResponse("user_organisation", res)

@organisationBlueprint.route('/getTeamMembers', methods=['GET'])
def getTeamMembers():
    token = request.args.get('token')

    if token == None:
        return genErrorResponse("Token missing", status=401)

    if not isTokenMaster(token):
        closeDBCon()
        return genErrorResponse("Not master token", status=403)

    res = getTeamMembersDB(token)
    closeDBCon()
    return genSuccessResponse("team_members", res)


if __name__ == "__main__":
    print("Not to be run directly")
    exit(1)
