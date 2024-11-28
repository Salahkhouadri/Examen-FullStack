package com.cinexpress.videofriend.services;

import java.util.List;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.DTOs.MovieDto;

public interface MovieService {

    void addMovie(MovieDto movie);

    void updateMovie(Long id,MovieDto movieDto);

    void updateAvailability(Long movieId, boolean availability);

    MovieDto getMovieById(Long id);

    List<Movie> listAllMovies();
    List<Movie> getMoviesByGenre(String genre);
    List<Movie> getMoviesByLanguage(String language);
    List<Movie> getMoviesByAvailability(Boolean availability);
    List<Movie> getMoviesByYear(int year);

    void deleteMovie(Long id);
}
