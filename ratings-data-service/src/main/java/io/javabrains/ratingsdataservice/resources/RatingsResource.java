package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    /**
     * TIP - We can return a List<Rating> but it is a bad idea to have array as root of json
     * Because when we need to add the field, it will have issues if u now add a onbject it won't be backward compatible
     * Also it will have issues while unmarshalling, what will you use below in last parameter - [Note u can use ParameterizedType but not a great idea]
     * So better idea is to create a new User Rating class
     *  UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
     * BAD IDEA - [{"movieId": 123, "rating": 4}, {"movieId": 5678, "rating": 3}]
     *
     */
    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }

}
