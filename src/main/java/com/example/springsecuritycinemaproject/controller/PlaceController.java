package com.example.springsecuritycinemaproject.controller;

import com.example.springsecuritycinemaproject.model.entity.Place;
import com.example.springsecuritycinemaproject.model.entity.Room;
import com.example.springsecuritycinemaproject.service.impl.PlaceServiceImpl;
import com.example.springsecuritycinemaproject.service.impl.RoomServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class PlaceController {

    PlaceServiceImpl placeService;
    RoomServiceImpl roomService;

    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("place", new Place());
        return "/place/save_place";
    }

    @PostMapping("/save_place")
    public String savePlace(@ModelAttribute Place place, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        place.setImage(multipartFile.getBytes());
        placeService.save(place);
        return "redirect:/place/all_places";
    }


    @GetMapping("/all_places")
    public String findAll(Model model) {
        model.addAttribute("all_places", placeService.findAll());
        return "/place/all_places";
    }

    @GetMapping("/find_all_by_id_room_id/{id}")
    public  String findAllRoomId(Model model,@PathVariable long id){
       List<Place>places=placeService.findAllByRoomId(id);
        model.addAttribute("place_id",places);
        return "/place/find_all_by_room_id";
    }

    @GetMapping("/findId/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("only_one_place", placeService.findById(id));
        return "/place/only_one_place";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "/place/update_place";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable long id) {
        placeService.update(id, place);
        return "redirect:/place/all_places";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        placeService.deleteById(id);
        return "redirect:/place/all_places";
    }

}

