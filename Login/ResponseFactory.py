#!/usr/bin/env python3
from flask import Response
import json

def genSuccessResponse(datatype, data):
    return Response(json.dumps({
        "status": "Success",
        "type": datatype,
        "data": data,
    }), status=200, mimetype='application/json', headers={
        "Content-Type": "application/json",
    })

def genErrorResponse(message, status=500):
    return Response(json.dumps({
        "status": "Error",
        "error_msg": message,
    }), status=status, mimetype='application/json', headers={
        "Content-Type": "application/json",
    })

notJsonError = genErrorResponse("Request payload must be JSON", status=400)

if __name__ == "__main__":
    print("Not to be executed directly")
    exit(1)
