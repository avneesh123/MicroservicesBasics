# MicroservicesBasics

## Theory => Service Discovery Pattern
### Client Side Discovery [Eureka]
1. Each service which wants to be discovered register with discovery server.
2. Client asks discovery server, I need to call Movie Rating service.
3. Discovery Service provides the address
4. Client calls the service
5. Spring Cloud uses this model
6. Drawback - It is little chatty

### Server Side Discovery
1. Client calls Discover Server - "Can you tell hi to Service 2"
2. Discovery Server passes the message
3. There are no extra hop here

 ##Implementation
 1.  Java 11 no longer has Jaxb, we need to add jaxb dependencies manually [check pom.xml]
 2.  Add spring-cloud-starter-netflix-eureka-server dependency, also add spring cloud dependency management
 3.  Application properties => Every Eureka Server is also a Eureka Client, as in prod, it not only provides registry
       but it also tries to register with other Eureka servers as we can have multiple Eureka servers, so that if one faile other can pick up
       - eureka.client.register-with-eureka=false
        - eureka.client.fetch-registry=false   --> don' t touch registry
 4. @EnableEurekaServer
 5. Add Eureka client dependency in client microservices rating, info and catalog service => spring-cloud-starter-netflix-eureka-client
 6. You can enable - @EnableEurekaClient [not mandatory]
 7. You can access the eureka dashboard at localhost:8761
 8. Fault Tolerance - 
     - Eureka Server has a Heartbeat signal to Client, so it knows if instance is down
     - If Eureka Server is down, client has cache data by default as well
