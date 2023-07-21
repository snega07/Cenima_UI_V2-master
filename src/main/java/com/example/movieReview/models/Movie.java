package com.example.movieReview.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String movieId;
  private String title;
  private String director;
  private String producer;
  private String motionPictureRating;
  @Column(length = 1500)
  private String movieDesc;
  private String runtime;
  private String collection;
  @Column(length = 1000)
  private String posterUrl;
  private String trailerId;
  @Transient
  private String rating;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties("movie")
  private Set<Genre> genres;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties("movie")
  private Set<Cast> casts;
  private String language;
  @Temporal(TemporalType.DATE)
  private LocalDate releaseDate;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Review> reviews = new ArrayList<>();

  @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Trending trending;

  protected Movie() {

  }

  public Movie(String movieId, String title, String director, String producer, String motionPictureRating,
      String movieDesc, String runtime, String collection, String posterUrl, Set<Genre> genres, String language,
      LocalDate releaseDate, Set<Cast> casts) {
    this.movieId = movieId;
    this.title = title;
    this.director = director;
    this.producer = producer;
    this.motionPictureRating = motionPictureRating;
    this.movieDesc = movieDesc;
    this.runtime = runtime;
    this.collection = collection;
    this.posterUrl = posterUrl;
    this.genres = genres;
    this.language = language;
    this.releaseDate = releaseDate;
    this.casts = casts;

  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getMotionPictureRating() {
    return motionPictureRating;
  }

  public void setMotionPictureRating(String motionPictureRating) {
    this.motionPictureRating = motionPictureRating;
  }

  public String getMovieDesc() {
    return movieDesc;
  }

  public void setMovieDesc(String movieDesc) {
    this.movieDesc = movieDesc;
  }

  public String getRuntime() {
    return runtime;
  }

  public void setRuntime(String runtime) {
    this.runtime = runtime;
  }

  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  public Set<Genre> getGenres() {
    return genres;
  }

  public void setGenres(Set<Genre> genres) {
    this.genres = genres;
  }

  public Set<Cast> getCasts() {
    return casts;
  }

  public void setCasts(Set<Cast> casts) {
    this.casts = casts;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public void addCast(Cast cast) {
    casts.add(cast);
    cast.setMovie(this);
  }

  public void addGenre(Genre genre) {
    genres.add(genre);
    genre.setMovie(this);
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

  public Trending getTrending() {
    return trending;
  }

  public void setTrending(Trending trending) {
    this.trending = trending;
    if (trending != null) {
      trending.setMovie(this);
    }
  }

  public String getTrailerId() {
    return trailerId;
  }

  public void setTrailerId(String trailerId) {
    this.trailerId = trailerId;
  }
}
