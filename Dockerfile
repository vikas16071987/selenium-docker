FROM bellsoft/liberica-openjdk-alpine:latest

RUN apk add curl jq

# Creating a workspace
WORKDIR /home/selenium-docker

#Add required files for our project
ADD target/docker-resources ./
ADD runner.sh                runner.sh



#RStart the script
ENTRYPOINT  sh runner.sh    