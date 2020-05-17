#!/usr/bin/evn python3

from functools import reduce
import base64
import random
import scrypt

def genRandomString(length):
    if length <= 0:
        return ""
    chars = ['1','2','3','4','5','6','7','8','9','0',
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    ]
    return str(reduce(lambda ans, x: ans + x, [random.choice(chars) for x in range(length)]))

def hashPassword(password, salt):
    return base64.b64encode(scrypt.hash('password', salt)).decode()

if __name__ == "__main__":
    print("Module not to be run directly")
    exit(1)
