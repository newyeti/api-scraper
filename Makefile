## Build image only

build-image:
	mvn clean compile jib:dockerBuild
gitpod-build-image:
	mvn -s maven-settings.xml clean compile jib:dockerBuild

## Build and push image to registry
build-image:
	mvn clean compile jib:build

gitpod-build-image:
	mvn -s maven-settings.xml clean compile jib:build

## Run docker containers defined in docker-compose.yml
up:
	docker compose up -d 

## Stop docker containers defined in docker-compose.yml
down:
	docker compose down