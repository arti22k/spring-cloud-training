#!/bin/sh

echo "******************************************************"
echo "Waiting for the Discovery server to start on port 8080"
echo "******************************************************"
while ! `nc -z discovery 8080`; do sleep 3; done
echo ">>>>>>>>>>>> Discovery Server has started"

echo "****************************************************"
echo "Waiting for the Config server to start on port 8080"
echo "****************************************************"
while ! `nc -z configuration 8080`; do sleep 3; done
echo ">>>>>>>>>>>> Config server has started"

echo "*****************************************************"
echo "Waiting for the Database server to start on port 5432"
echo "*****************************************************"
while ! `nc -z organization_database 5432`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

java -jar -Dspring.cloud.config.uri=$CONFIG_URI /usr/local/service/organization.jar
