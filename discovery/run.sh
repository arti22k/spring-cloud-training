#!/bin/sh

java -jar -Deureka.client.serviceUrl.defaultZone=$ZONE_URI /usr/local/service/discovery.jar
