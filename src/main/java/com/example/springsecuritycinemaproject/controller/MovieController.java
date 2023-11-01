package com.example.springsecuritycinemaproject.controller;

import com.example.springsecuritycinemaproject.model.entity.Movie;
import com.example.springsecuritycinemaproject.service.impl.MovieServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class MovieController {

    MovieServiceImpl movieService;


    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("movie", new Movie());
        return "/movie/save_movie";
    }

    @PostMapping("/save_movie")
    public String saveMovie(@ModelAttribute Movie movie, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        movie.setImage(multipartFile.getBytes());
        movieService.save(movie);
        return "redirect:/movie/all_movies";
    }


    @GetMapping("/all_movies")
    public String findAll(Model model) {
        model.addAttribute("all_movies", movieService.findAll());
        return "/movie/all_movies";
    }

    @GetMapping("/findId/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("only_one_movies", movieService.findById(id));
        return "/movie/find_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        model.addAttribute("movie", movieService.findById(id));
        return "/movie/update_movie";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable long id, @RequestParam("file") MultipartFile multipartFile) throws IOException{
        movie.setImage(multipartFile.getBytes());
        movieService.update(id, movie);
        return "redirect:/movie/all_movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        movieService.deleteById(id);
        return "redirect:/movie/all_movies";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        if (movie != null && movie.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(movie.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
