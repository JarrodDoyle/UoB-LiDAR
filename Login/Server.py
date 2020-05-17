#!/usr/bin/env python3
from flask import Flask, request, Response
from waitress import serve
from flask_cors import CORS
from AccountHandler import accountBlueprint
from SiteHandler import siteBlueprint
from OrganisationHandler import organisationBlueprint

app = Flask(__name__)
CORS(app)

app.register_blueprint(accountBlueprint)
CORS(accountBlueprint)

app.register_blueprint(siteBlueprint, url_prefix="/sites")
CORS(siteBlueprint)

app.register_blueprint(siteBlueprint, url_prefix="/lidars")
CORS(siteBlueprint)

app.register_blueprint(organisationBlueprint)
CORS(organisationBlueprint)

if __name__ == "__main__":
    serve(app, host='0.0.0.0', port=6000)
    #app.run(host='0.0.0.0', port=6000)
