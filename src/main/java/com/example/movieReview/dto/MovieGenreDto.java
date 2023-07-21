package com.example.movieReview.dto;



public class MovieGenreDto {
    private String title;
    private String movieId;
    private String posterUrl;
    private String rating;
    private String category;
    private String movieDesc;
   

    public MovieGenreDto() {

    }

    public MovieGenreDto(String title,String movieId, String posterUrl,
            String category,String movieDesc) {
        this.title = title;
        this.movieId=movieId;
        this.posterUrl = posterUrl;
        this.category = category;
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    
    

}
