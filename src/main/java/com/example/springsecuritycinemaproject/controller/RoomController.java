package com.example.springsecuritycinemaproject.controller;

import com.example.springsecuritycinemaproject.model.entity.Cinema;
import com.example.springsecuritycinemaproject.model.entity.Room;
import com.example.springsecuritycinemaproject.service.impl.CinemaServiceImpl;
import com.example.springsecuritycinemaproject.service.impl.RoomServiceImpl;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class RoomController {

    RoomServiceImpl roomService;
    CinemaServiceImpl cinemaService;

    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList() {
        return cinemaService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        List<Cinema>cinemaList=cinemaService.findAll();
        model.addAttribute("room", new Room());
        model.addAttribute("cinema",cinemaList);
        return "/room/save_room";
    }

    @PostMapping("/save_room")
    public String saveRoom(@ModelAttribute Room room, @RequestParam("file") MultipartFile multipartFile) throws IOException {
       room.setImage(multipartFile.getBytes());
        roomService.save(room);
        return "redirect:/room/all_rooms";
    }


    @GetMapping("/all_rooms")
    public String findAll(Model model) {
        model.addAttribute("all_rooms_id", roomService.findAll());
        return "/room/all_rooms";
    }

    @GetMapping("/find_all_cinema_id/{id}")
    public String findAllCinemaId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rooms", roomService.findAllByCinemaId(id));
        return "/room/find_all_by_cinema_id";
    }

    @GetMapping("/findId")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("only_one_room", roomService.findById(id));
        return "/room/only_one_room";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "/room/update_room";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable long id) {
        roomService.update(id, room);
        return "redirect:/room//all_rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        roomService.deleteById(id);
        return "redirect:/room/all_rooms";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        Room room = roomService.findById(id);
        if (room != null && room.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(room.getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
