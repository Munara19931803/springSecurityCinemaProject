package com.example.springsecuritycinemaproject.service.impl;

import com.example.springsecuritycinemaproject.exception.EntityNotFoundException;
import com.example.springsecuritycinemaproject.model.entity.Cinema;
import com.example.springsecuritycinemaproject.model.entity.Movie;
import com.example.springsecuritycinemaproject.model.entity.Session;
import com.example.springsecuritycinemaproject.repository.MovieRepository;
import com.example.springsecuritycinemaproject.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class MovieServiceImpl implements ServiceLayer<Movie> {

    MovieRepository movieRepository;

    @Override
    public void save(Movie movie) {
      movieRepository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Movie with id= %d not found!!!", id)));
    }
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    public  List<Movie>findAllMovie(long id){
      return   movieRepository.findAllById(id);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie oldMovie = findById(id);
        oldMovie.setName(movie.getName());
        oldMovie.setGenres(movie.getGenres());
        oldMovie.setCreated(movie.getCreated());
        oldMovie.setCountry(movie.getCountry());
        oldMovie.setLanguage(movie.getLanguage());
        oldMovie.setImage(movie.getImage());
        movieRepository.save(oldMovie);
        return oldMovie;
    }

    @Override
    public void deleteById(Long id) {
    movieRepository.deleteById(id);
    }

}
