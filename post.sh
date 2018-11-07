#!/usr/bin/env bash
curl -i --header "Content-Type: application/json" \
     --request POST \
     --data "$2" \
     "http://localhost:8080/jersey/users/$1"
