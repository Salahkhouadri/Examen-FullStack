package com.cinexpress.videofriend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.DTOs.MovieDto;
import com.cinexpress.videofriend.repository.MovieRepository;
import com.cinexpress.videofriend.services.MovieService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieServiceImpl implements MovieService{

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void addMovie(MovieDto movie) {
        Movie movieToSave = new Movie();
        movieToSave.setId(0L);
        movieToSave.setTitle(movie.getTitle());
        movieToSave.setFormat(movie.getFormat());
        movieToSave.setGenre(movie.getGenre());
        movieToSave.setLanguage(movie.getLanguage());
        movieToSave.setYear(movie.getYear());
        movieToSave.setAvailability(movie.getAvailability());
        movieRepository.save(movieToSave);
    }

    @Override
    public void updateAvailability(Long movieId, boolean availability) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()){
            movie.get().setAvailability(availability);
            movieRepository.save(movie.get());
        }else{
            throw new UnsupportedOperationException("Unimplemented method 'updateAvailability'");
        }
    }

    @Override
    public void updateMovie(Long id,MovieDto movieDto) {
        Optional<Movie> movieUpdate = movieRepository.findById(id);
    if (movieUpdate.isPresent()) {
        Movie existingMovie = movieUpdate.get();
        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setFormat(movieDto.getFormat());
        existingMovie.setGenre(movieDto.getGenre());
        existingMovie.setLanguage(movieDto.getLanguage());
        existingMovie.setYear(movieDto.getYear());
        existingMovie.setAvailability(movieDto.getAvailability());
        movieRepository.save(existingMovie);
    } else {
        throw new EntityNotFoundException("Movie not found with ID: " + id);
    }
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movie.get().getTitle());
            movieDto.setFormat(movie.get().getFormat());
            movieDto.setGenre(movie.get().getGenre());
            movieDto.setLanguage(movie.get().getLanguage());
            movieDto.setYear(movie.get().getYear());
            movieDto.setAvailability(movie.get().getAvailability());
            return movieDto;
        } else {
            throw new EntityNotFoundException("Movie not found with ID: " + id);
        }
    }

    @Override
    public List<Movie> listAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguage(language);
    }

    @Override
    public List<Movie> getMoviesByAvailability(Boolean availability) {
        return movieRepository.findByAvailability(availability);
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        return movieRepository.findByYear(year);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    
}
