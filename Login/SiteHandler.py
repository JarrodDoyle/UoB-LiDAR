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

def kpiDataText(key, text):
    return {
        "id": key,
        "type": "text",
        "text": text,
    }

def kpiDataNumber(key, text, number):
    return {
        "id": key,
        "type": "numeric",
        "text": text,
        "data": number,
    }

def kpiDataGraph(graphType, data):
    return {
        "id": key,
        "type": "graph",
        "graphType": graphType,
        "data": data,
    }

@siteBlueprint.route('/getKPIs', methods=['GET'])
def getKPIs():
    r = requests.get("http://localhost:8080/database/EXBUOY/kpis").json()
    kpis = []

    for k, v in r.items():
        if k == "mast" or k == "serial":
            continue
        summary = []
        summary.append(kpiDataText("rsqr heading", "R Squared"))
        for hight, vals in v.items():
            summary.append(
                kpiDataNumber(hight, hight, vals["rSqr"]["a"] if type(vals["rSqr"]) is dict else vals["rSqr"]),
            )
        kpis.append({
            "id": k,
            "name": k,
            "description": k,
            "percentComplete": 100,
            "summary": summary,
            "data": [],
        })
    kpis.append({
        "id": "maintinanceVisits",
        "name": "Maintinance Visits",
        "description": "Visits to perform maintinance tasks",
        "percentComplete": 100,
        "summary": [
            kpiDataNumber("card-scheduled", "Scheduled Visits", 0),
            kpiDataNumber("card-unscheduled", "Unscheduled Visits", 0),
        ],
        "data": [
            kpiDataNumber("card-scheduled", "Scheduled Visits", 0),
            kpiDataNumber("card-unscheduled", "Unscheduled Visits", 0),
        ]
    })

    return genSuccessResponse(f'{g.type} get', kpis)

if __name__ == "__main__":
    print ("Not to be run directly")
    exit(1)
