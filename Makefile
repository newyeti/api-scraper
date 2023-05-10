## Compile Source code
compile:
	mvn -s maven-settings.xml clean compile

gitpod-compile:
	mvn -s maven-settings.xml clean compile

## Build image only
build:
	mvn clean compile jib:dockerBuild
gitpod-build:
	mvn -s maven-settings.xml clean compile jib:dockerBuild

## Build and push image to registry
build-image:
	mvn clean package jib:build

gitpod-build-image:
	mvn -s maven-settings.xml clean package jib:build

## Run docker containers defined in docker-compose.yml
up:
	docker compose up -d 

## Stop docker containers defined in docker-compose.yml
down:
	docker compose down