#!/bin/bash
gradle build

docker-compose up --force-recreate locust
