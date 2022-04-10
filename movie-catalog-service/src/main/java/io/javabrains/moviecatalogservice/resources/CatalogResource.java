package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    //TIP - This is going to be deprecated
    @Autowired
    private RestTemplate restTemplate;

    /**
     * TIP - Used For Reactive - Need Reactive Web [spring-boot-starter-webflux] it comes with Netty
     * Uses Builder Pattern
     * webClientBuilder.build()
     *  .get()
     *  .uri("http://localhost:8082/movies/" + rating.getMovieId())
     *  .retrieve()
     *  .bodyToMono(Movie.class)  // this will give Mono<Movie> which is a promise  once it's there it will convert it to Movie
     *  .block()
     *
     *  Also look at the return type we can either return a List<Movie> which we are doing here [ bcz of block() method above]
     *  Or a better OR reactive way is to return a Mono
     */
    @Autowired
    WebClient.Builder webClientBuilder;



    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //TIP here ratings-data-service is a hint to EUREKA server this is done using @LoadBalanced Annotation
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
        System.out.println("userdata - "+ userRating.getUserId());
        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());

    }
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/