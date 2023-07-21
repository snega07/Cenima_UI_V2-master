package com.example.movieReview.dto;

public class MovieDto {
    private String title;
    private String movieId;
    private String posterUrl;
    private String rating;
    private String castName;
    private String movieDesc;

    public MovieDto() {

    }

    public MovieDto(String title, String movieId, String posterUrl,
            String castName, String movieDesc) {
        this.title = title;
        this.movieId = movieId;
        this.posterUrl = posterUrl;
        this.castName = castName;
        this.movieDesc = movieDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getCastName() {
        return castName;
    }

    public void setCastName(String castName) {
        this.castName = castName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
