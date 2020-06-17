#!/bin/bash
gradle build

docker-compose up --force-recreate -d checkout-main
docker-compose build checkout-main
docker-compose run checkout-main -parallel
#bash -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:8080/products/)" != "200" ]]; do sleep 5; printf "Waiting 5 more seconds...\n" ; done'
