package com.example.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TIP :: Changes required for this application
 * 1.  Java 11 no longer has Jaxb dependencies, we need to add jaxb check pom.xml
 * 2. Add spring-cloud-starter-netflix-eureka-server dependency, also add spring cloud dependcy management
 * 3. Application properties => Every Eureka Server is also a Eureka Client, as in prod, it not only provides registry
 *      but it also tries to register with other Eureka servers as we can have multiple Eureka servers, so that if one faile other can pick up
 *   - eureka.client.register-with-eureka=false
 *   - eureka.client.fetch-registry=false   --> don' t touch registry
 *
 * 4. @EnableEurekaServer
 * 5. Add dependency in client microservices rating, info and catalog service => spring-cloud-starter-netflix-eureka-client
 * 6. You can enable - @EnableEurekaClient [not mandatory]
 * 7. You can access the eureka dashboard at localhost:8761
 * 8.
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}
