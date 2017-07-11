FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade
RUN mkdir -p /usr/local/service
ADD ./target/organization.jar /usr/local/service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
