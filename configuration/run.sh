#!/bin/sh

echo "*****************************************************"
echo "Waiting for the RabbitMQ server to start on port 5672"
echo "*****************************************************"
while ! `nc -z rabbitmq 5672`; do sleep 3; done
echo ">>>>>>>>>>>> RabbitMQ server has started"

java -jar -Dspring.rabbitmq.host=$RABBITMQ_HOST -Dspring.rabbitmq.username=$RABBITMQ_USERNAME -Dspring.rabbitmq.password=$RABBITMQ_PASSWORD /usr/local/service/configuration.jar
