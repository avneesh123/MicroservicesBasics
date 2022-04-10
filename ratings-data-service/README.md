###Miscellaneous
- In class RatingResource.java and methos getUserRating()
- We can return a List<Rating> but it is a bad idea to have array as root of json
- Because when we need to add the field, it will have issues if u now add a onbject it won't be backward compatible
- Also it will have issues while unmarshalling, what will you use below in last parameter - [Note u can use ParameterizedType but not a great idea]
- So better idea is to create a new User Rating class
- UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
-  BAD IDEA - [{"movieId": 123, "rating": 4}, {"movieId": 5678, "rating": 3}]
