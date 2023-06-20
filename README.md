# API-SCRAPER

## Configuration
- Discover Server
- Config Server
- API Gateway
- Observability
- Distributed Tracing

### Discover Server
Discover Server is a service registry used to discover the microservices running in the container. It uses Eureka Server/Client package.

### Config Server
Config Server allows to put common configuration at one place when working with multiple microservices. It allows to avoid redundency and helps to maintain the common application configurations.

### API Gateway
API Gateway is the reverse proxy for microservices. It is the gateway to access all the microservices in the container. The following are the services provided by API Gateway.
- Reverse Proxy to APIs
- Authentication
- Fallback method
- Rate Limit

### Observability
Observability is provided by Micrometer.

### Distributed Tracing 
Tracing is provided with Zipkin.


## Provided Containers
- Zookeeper
- Kafka Broker
- Schema Registry
- Schema Registry UI
- Control Central for Kafka Broker
- KeyCloak
- MongoDB
- Redis
- Zipkin
- ELK services
- Discovery Server
- Config Server
- API Gateway

## Create new project
- Create a new repository using microservice-template project
- Create a new module in new repository
- Add new module in main pom.xml

## Build and Run

### Build image only
```  
  # Local build
  make build-image
  
  # Gitpod build
  gitpod-build-image
```

### Build image and push it to registry
```  
  # Local build
  make build
  
  # Gitpod build
  gitpod-build
```

### Run docker containers defined in docker-compose.yml
```
  make up

  OR

  docker compose up -d
```

### Stop docker containers defined in docker-compose.yml
```
  make down

  OR

  docker compose down
```

#### Envrionment Variables
```
export EUREKA_USERNAME=eureka
export EUREKA_PASSWORD= password
export DISCOVERY_SERVER_HOSTNAME=https://8761-${HOSTNAME}.${GITPOD_WORKSPACE_CLUSTER_HOST}
export MONGO_INITDB_ROOT_USERNAME=admin
export MONGO_INITDB_ROOT_PASSWORD=password \
export FOOTBALL_DB_PASSWORD=football
export APP_CONFIG_DB_PASSWORD=app_config
````
