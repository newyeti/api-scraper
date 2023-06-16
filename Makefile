## Compile Source code
compile:
ifeq ($(strip $(module)),)
	mvn clean install 
else
	@echo "Build image for $(module)"
	mvn clean install -f $(module)
endif

gcompile:
ifeq ($(strip $(module)),)
	mvn -s maven-settings.xml clean install 
else
	@echo "Build image for $(module)"
	mvn -s maven-settings.xml clean install -f $(module)
endif

## Build image only
dockerBuild:
ifeq ($(strip $(module)),)
	mvn clean compile jib:dockerBuild
else
	@echo "Build image for $(module)"
	mvn clean compile jib:dockerBuild -f $(module)
endif

	
gdockerBuild:
ifeq ($(strip $(module)),)
	mvn -s maven-settings.xml clean compile jib:dockerBuild
else
	@echo "Build image for $(module)"
	mvn -s maven-settings.xml clean compile jib:dockerBuild -f $(module)
endif
	

## Build and push image to registry
build:
ifeq ($(strip $(module)),)
	mvn clean package jib:build
else
	@echo "Build image for $(module)"
	mvn clean package jib:build -f $(module)
endif
	

gbuild:
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