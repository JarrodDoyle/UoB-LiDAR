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

def register(client, email, password):
    return client.post('/register', json={
        "email": email,
        "password": password
    }, follow_redirects=True)

def test_register(client):
    rv = register(client, "a@a.com", "a")
    assert b'Success' in rv.data

def test_register_exists(client):
    rv = register(client, "a@a.com", "a")
    assert b'Account already exists' in rv.data

def test_login(client):
    rv = login(client, "a@a.com", "a")
    assert b'master_key' in rv.data

def test_login_bad(client):
    rv = login(client, "b@b.com", "b")
    assert b'Invalid username or password' in rv.data
