#!/bin/sh

echo "*******************************************************"
echo "Waiting for the Discovery server to start on port 8080"
echo "*******************************************************"
while ! `nc -z discovery 8080`; do sleep 3; done
echo ">>>>>>>>>>>> Service discovery Server has started"

java -jar -Deureka.client.serviceUrl.defaultZone=$ZONE_URI /usr/local/service/gateway.jar
