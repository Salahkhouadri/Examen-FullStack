package com.cinexpress.videofriend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.DTOs.MovieDto;
import com.cinexpress.videofriend.services.MovieService;

@RestController
@RequestMapping ("/movie")
public class MovieController {


    MovieService  movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getMovies(
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String language,
        @RequestParam(required = false) Boolean availability,
        @RequestParam(required = false) Integer year) {
    
    if (genre != null) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    } else if (language != null) {
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    } else if (availability != null) {
        return ResponseEntity.ok(movieService.getMoviesByAvailability(availability));
    } else if (year != null) {
        return ResponseEntity.ok(movieService.getMoviesByYear(year));
    }
    return ResponseEntity.ok(movieService.listAllMovies());

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id)); 
    }
    
    @PostMapping("/")
    public ResponseEntity<Void> addMovie(@RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable("id") Long id, @RequestBody MovieDto movieDto) {
        movieService.updateMovie(id, movieDto);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAvailability(@PathVariable("id") Long id, @RequestBody Boolean availability) {
        movieService.updateAvailability(id, availability);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }


    
}
