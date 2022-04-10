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
