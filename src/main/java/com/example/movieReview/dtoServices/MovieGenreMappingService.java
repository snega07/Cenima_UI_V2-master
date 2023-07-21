package com.example.movieReview.dtoServices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movieReview.dto.MovieDto;
import com.example.movieReview.dto.MovieGenreDto;
import com.example.movieReview.models.Cast;
import com.example.movieReview.models.CastRepository;
import com.example.movieReview.models.Genre;
import com.example.movieReview.models.GenreRepository;
import com.example.movieReview.models.Movie;
import com.example.movieReview.models.ReviewRepository;

@Service
public class MovieGenreMappingService {

    @Autowired
    private GenreRepository genreRepository;
     @Autowired
    private ReviewRepository reviewRepository;
   

     public List<MovieGenreDto> getAllMoviesForGenre(String category) {
        return genreRepository.findByCategoryContaining(category)
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
     private MovieGenreDto convertDataIntoDTO (Genre genre) {

        MovieGenreDto dto = new MovieGenreDto();
        dto.setCategory(genre.getCategory());

        Movie movie = genre.getMovie();
        if (movie != null) {
                if (reviewRepository.rating(movie.getMovieId()) != null) {
                    movie.setRating(reviewRepository.rating(movie.getMovieId()));
                }
            }
        dto.setRating(movie.getRating());
        dto.setMovieId(movie.getMovieId());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setMovieDesc(movie.getMovieDesc());
        dto.setTitle(movie.getTitle());
      
      return dto;
       
     }  
    
}
