
package com.example.movieReview.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.movieReview.Exception.ResourceNotFoundException;
import com.example.movieReview.dto.MovieGenreDto;
import com.example.movieReview.dtoServices.MovieGenreMappingService;
import com.example.movieReview.models.Cast;
import com.example.movieReview.models.Genre;
import com.example.movieReview.models.Movie;
import com.example.movieReview.models.MovieRepository;
import com.example.movieReview.models.ReviewRepository;

import org.springframework.http.HttpStatus;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@ResponseBody
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieGenreMappingService movieGenreMappingService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository, ReviewRepository reviewRepository,
           MovieGenreMappingService movieGenreMappingService) {
        this.movieRepository = movieRepository;
        this.movieGenreMappingService = movieGenreMappingService;
        this.reviewRepository = reviewRepository;
    }

    // add movie
    @PostMapping("api/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
       if (movie.getCasts() != null) {
            for (Cast cast : movie.getCasts())
            if(cast.getCastName()!=""){
                movie.addCast(cast);
            }
        }

         if (movie.getGenres() != null) {
            for (Genre genre : movie.getGenres())
            if(genre.getCategory()!=""){
                movie.addGenre(genre);
            }
        }

        Movie _movie = movieRepository.save(movie);
        return new ResponseEntity<>(_movie, HttpStatus.CREATED);

    }

    // retrives all the movie
    @GetMapping("/api/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Movie mov : movies) {
            if (mov != null) {
                if (reviewRepository.rating(mov.getMovieId()) != null) {
                    mov.setRating(reviewRepository.rating(mov.getMovieId()));
                }
            }
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //retrives movie that has rating greater than 4
    @GetMapping("api/movies/recommendation")
    public ResponseEntity<List<Movie>> getMoviesForRecommendation(){
        List<Movie> movies = movieRepository.findAll();
        List<Movie> rMovies = new ArrayList<>();
        for (Movie mov : movies) {
            if (mov != null) {
                if (reviewRepository.rating(mov.getMovieId()) != null) {
                    mov.setRating(reviewRepository.rating(mov.getMovieId()));
                }
                if (mov.getRating() != null && Double.parseDouble(mov.getRating()) >= 4) {
                rMovies.add(mov);
            }
            }
        }
         return new ResponseEntity<>(rMovies, HttpStatus.OK);

    }

    // get movie detail by id
    @GetMapping("api/movies/{movieId}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable String movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        movie.setRating(reviewRepository.rating(movieId));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // update movie detail by id
    @PutMapping("api/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("movieId") String movieId, @RequestBody Movie movie) {
        Movie _movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("not found movie with id" + movieId));

        if (movie.getCasts() != null) {
            for (Cast cast : movie.getCasts())
            if(cast.getCastName()!=""){
                _movie.addCast(cast);
            }
        }

         if (movie.getGenres() != null) {
            for (Genre genre : movie.getGenres())
            if(genre.getCategory()!=""){
                _movie.addGenre(genre);
            }
        }
        //System.out.println(movie.getGenres());
        _movie.setTitle(movie.getTitle());
        _movie.setRuntime(movie.getRuntime());
        _movie.setReleaseDate(movie.getReleaseDate());
        _movie.setProducer(movie.getProducer());
        _movie.setMovieDesc(movie.getMovieDesc());
        _movie.setMotionPictureRating(movie.getMotionPictureRating());
        _movie.setLanguage(movie.getLanguage());
        _movie.setDirector(movie.getDirector());
        _movie.setCollection(movie.getCollection());
        _movie.setPosterUrl(movie.getPosterUrl());
        return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);

    }

    // deletes all the movies
    @DeleteMapping("api/movies")
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        movieRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete movie by movieId
    @DeleteMapping("api/movies/{movieId}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("movieId") String movieId) {
        Movie _movie = movieRepository.findById(movieId).orElse(null);
        _movie.getGenres().clear();
        movieRepository.deleteById(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // search movie by title
    @GetMapping("api/movies/search/title")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam("title") String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        for (Movie mov : movies) {
            if (mov != null) {
                if (reviewRepository.rating(mov.getMovieId()) != null) {
                    mov.setRating(reviewRepository.rating(mov.getMovieId()));
                }
            }

        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

   // search movie by genre
    @GetMapping("/api/movies/search/genre")
 public ResponseEntity<List<MovieGenreDto>> getmoviesForCast(@RequestParam("category") String category) {
        List<MovieGenreDto> movieGnereDtoList = movieGenreMappingService.getAllMoviesForGenre(category);
        return new ResponseEntity<>(movieGnereDtoList, HttpStatus.OK);
    }



}
