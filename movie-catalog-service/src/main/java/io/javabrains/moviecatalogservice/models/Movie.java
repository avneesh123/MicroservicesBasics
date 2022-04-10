package io.javabrains.moviecatalogservice.models;

public class Movie {
    private String movieId;
    private String name;
    private String description;

    //TIP : Since we are Marshalling this object [in Catalog Resource] an empty constructor is necessary
    public Movie() {
    }



    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
