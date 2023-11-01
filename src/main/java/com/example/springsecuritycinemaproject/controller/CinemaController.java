package com.example.springsecuritycinemaproject.controller;

import com.example.springsecuritycinemaproject.model.entity.Cinema;
import com.example.springsecuritycinemaproject.service.impl.CinemaServiceImpl;
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
@RequestMapping("/cinema")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CinemaController {

    CinemaServiceImpl cinemaService;


    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("cinema", new Cinema());
        return "/cinema/cinema_save";
    }

    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        cinema.setImage(multipartFile.getBytes());
        cinemaService.save(cinema);
        return "redirect:/cinema/all_cinemas";
    }

    @GetMapping("/all_cinemas")
    public String findAll(Model model) {
        model.addAttribute("all_cinemas", cinemaService.findAll());
        return "cinema/all_cinemas";
    }

    @GetMapping("/findId/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("only_one_cinema", cinemaService.findById(id));
        return "/cinema/find_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        model.addAttribute("cinema",cinemaService.findById(id));
        return "/cinema/update_cinema";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable long id,@RequestParam("file") MultipartFile multipartFile)throws IOException {
        cinema.setImage(multipartFile.getBytes());
        cinemaService.update(id, cinema);
        return "redirect:/cinema/all_cinemas";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cinemaService.deleteById(id);
        return "redirect:/cinema/all_cinemas";
    }

    @GetMapping("/main_page")
    public String getMainPage() {
        return "/cinema/ciNema";
    }
    @GetMapping("/pay")
    public String getMainPay() {
        return "/cinema/pay";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema != null && cinema.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(cinema.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
