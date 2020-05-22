#!/usr/bin/env python3
from flask import Blueprint, request, g
from ResponseFactory import *
from DatabaseHandler import getLiDARS, getSites, hasWritePerms
from itertools import chain
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

    if g.type == "sites":
        return genSuccessResponse("sites", getSites(token))

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

def kpiDataGraph(key, graphType, data):
    return {
        "id": key,
        "type": "graph",
        "graphType": graphType,
        "data": data,
    }

def kpiDataTable(key, rows):
    return {
        "id": key,
        "type": "table",
        "data": rows,
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

        data = []
        rows = [list(chain.from_iterable([(x + "_a", x + "_b") for x in vals.keys()])) if type(list(vals.values())[0]) is dict else list(vals.keys())]
        for hight, vals in v.items():
            rows.append(list(map(lambda x: float(x),
                list(vals.values())
                if type(list(vals.values())[0]) is not dict
                else [x for y in vals.values() for x in y.values()]
            )))

        data.append(kpiDataTable(f'{k}-details',rows))
        kpis.append({
            "id": k,
            "name": k,
            "description": k,
            "percentComplete": 100,
            "summary": summary,
            "data": data,
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

@siteBlueprint.route('/<int:key>/sendjson', methods=['POST'])
def sendjons(key):
    token = request.args.get('token')
    if token == None:
        return genErrorResponse("Token missing", status=401)
    if not hasWritePerms(token, g.type, key):
        return genErrorResponse("Insuffient permissions", status=401)
    requests.post(f"http://localhost:8080/database/{key}/sendjson", data=request.getJson())
    return genSuccessResponse("uplaod", "success")

@siteBlueprint.route('/<int:key>/sendcsv', methods=['POST'])
def sendcsv(key):
    token = request.args.get('token')
    if token == None:
        return genErrorResponse("Token missing", status=401)
    if not hasWritePerms(token, g.type, key):
        return genErrorResponse("Insuffient permissions", status=401)
    requests.post(f"http://localhost:8080/database/{key}/sendcsv", data=request.get_data())
    return genSuccessResponse("uplaod", "success")

if __name__ == "__main__":
    print ("Not to be run directly")

    exit(1)
