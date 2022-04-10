package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

	/**
	 * //TIP - Create RestTemplate as @Bean - and inject it everywhere, rather than initiating in a method
	 * /@Autowire it is CONSUMER that is u r asking Spring to give me something e.g @Autowire private RestTemplate and
	 * /@Bean is a PRODUCER i.e you are telling SPRING to lazy Initialize [ when any method using it get called] and put it in container
	 *
	 * @LoadBalanced - We are telling RestTemplate to call service discovery everytime
	 * We give RestTemplate a url hint like [http://ratings-data-service/ratingsdata/user/]
	 * It will go to Eureka Server, it will give the server and port and client can make a call
	 * It will also do LoadBalancing  - say client ask for service1 and say it has 3 instances registered, it picks up instances round robin
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

