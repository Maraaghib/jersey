#!/usr/bin/env bash
curl -i --header "Content-Type: application/json" --request POST --data "$1" http://localhost:8080/jersey/users
