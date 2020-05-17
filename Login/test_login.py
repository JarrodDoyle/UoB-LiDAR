#!/usr/bin/env python3

import pytest
import Server

@pytest.fixture
def client():
    with Server.app.test_client() as client:
        yield client

def login(client, email, password):
    return client.post('/login', json={
        "email": email,
        "password": password
        }, follow_redirects=True)

def test_login(client):
    rv = login(client, "a@a.com", "a")
    assert b'master_key' in rv.data
