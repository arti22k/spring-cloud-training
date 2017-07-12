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
while ! `nc -z users_database 5432`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "*****************************************************"
echo "Waiting for the RabbitMQ server to start on port 5672"
echo "*****************************************************"
while ! `nc -z rabbitmq 5672`; do sleep 3; done
echo ">>>>>>>>>>>> RabbitMQ server has started"

java -jar -Dspring.cloud.stream.bindings.kafka.binder.zkNodes=$KAFKA_HOST -Dspring.cloud.stream.bindings.kafka.binder.brokers=$KAFKA_HOST -Dspring.cloud.config.uri=$CONFIG_URI -Dspring.rabbitmq.host=$RABBITMQ_HOST -Dspring.rabbitmq.username=$RABBITMQ_USERNAME -Dspring.rabbitmq.password=$RABBITMQ_PASSWORD /usr/local/service/users.jar
