## Compile Source code
compile:
ifeq ($(strip $(module)),)
	mvn clean compile 
else
	@echo "Build image for $(module)"
	mvn clean compile -f $(module)
endif

gitpod-compile:
ifeq ($(strip $(module)),)
	mvn -s maven-settings.xml clean compile 
else
	@echo "Build image for $(module)"
	mvn -s maven-settings.xml clean compile -f $(module)
endif

## Build image only
build:
ifeq ($(strip $(module)),)
	mvn clean compile jib:dockerBuild
else
	@echo "Build image for $(module)"
	mvn clean compile jib:dockerBuild -f $(module)
endif

	
gitpod-build:
ifeq ($(strip $(module)),)
	mvn -s maven-settings.xml clean compile jib:dockerBuild
else
	@echo "Build image for $(module)"
	mvn -s maven-settings.xml clean compile jib:dockerBuild -f $(module)
endif
	

## Build and push image to registry
build-image:
ifeq ($(strip $(module)),)
	mvn clean package jib:build
else
	@echo "Build image for $(module)"
	mvn clean package jib:build -f $(module)
endif
	

gitpod-build-image:
ifeq ($(strip $(module)),)
	mvn -s maven-settings.xml clean package jib:build
else
	@echo "Build image for $(module)"
	mvn -s maven-settings.xml clean package jib:build -f $(module)
endif
	
## Run docker containers defined in docker-compose.yml
up:
	docker compose up -d 

## Stop docker containers defined in docker-compose.yml
down:
	docker compose down