###Rest Template Creation
 - Create RestTemplate as @Bean - and inject it everywhere, rather than initiating in a method.
 - @Autowire it is CONSUMER, you are asking Spring to give you something e.g @Autowire private RestTemplate.
 - @Bean is a PRODUCER i.e you are telling SPRING to lazy Initialize [when any method using it gets called] and put it in container.
### Client Side Load Balancing
- We give RestTemplate a hint like [http://ratings-data-service/ratingsdata/user/].
- RestTemplate will call service discovery everytime.
- It will go to Eureka Server, get server and port and client can make a call.
- It will also perform client side LoadBalancing  - say client ask for service1 and say it has 3 instances registered, it picks up the instance in round robin.
- We can override this behavior by Autowiring the client
### Miscellaneous
 - Whenever we use an object or unmarshalling it should have have default constructor 
 -   MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
 -   Here Movie Summary class, if it has a parametrized constructor then default constructor is required.
 -   RestTemplate is getting depricated pretty soon and WebClientBuilder will be the new norm, see below for blocking call
 -   It blocking because we return a List<Movie> [ bcz of block() method below],  or a better OR reactive way is to return a Mono
  -  For WebClient we need Reactive Web [spring-boot-starter-webflux] it comes with Netty 
 ```
 webClientBuilder.build().get()
         .uri("http://localhost:8082/movies/" + rating.getMovieId())
         .retrieve()
         .bodyToMono(Movie.class)  // this will give Mono<Movie> which is a promise  once it's there it will convert it to Movie
         .block() 
  ```
