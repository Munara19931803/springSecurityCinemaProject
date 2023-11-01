package com.example.springsecuritycinemaproject.controller;

import com.example.springsecuritycinemaproject.model.entity.Room;
import com.example.springsecuritycinemaproject.model.entity.Session;
import com.example.springsecuritycinemaproject.service.impl.RoomServiceImpl;
import com.example.springsecuritycinemaproject.service.impl.SessionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/session")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class SessionController {

    SessionServiceImpl sessionService;
    RoomServiceImpl roomService;

    @ModelAttribute("roomList")
    public List<Room> roomList(){
        return roomService.findAll();
    }


    @GetMapping("/save")
    public String save(Model model) {
        Session session= new Session();
        model.addAttribute("save1_session", session);
        return "/session/save_session";
    }

    @PostMapping("/save_session")
    public String saveSession(@ModelAttribute Session session, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        session.setImage(multipartFile.getBytes());
        session.setFinish(LocalDateTime.now().plusHours(session.getDuration()));
        sessionService.save(session);
        return "redirect:/session/all_sessions";
    }


    @GetMapping("/all_sessions")
    public String findAll(Model model) {
        model.addAttribute("all_sessions", sessionService.findAll());
        return "/session/all_sessions";
    }
    @GetMapping("/all_session/{id}")
    public String findAllId(@PathVariable("id")long id, Model model){
        model.addAttribute("all_session_id",sessionService.findAllId(id));
        return "/session/all_movie_id";
    }

    @GetMapping("/findId")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("only_one_session", sessionService.findById(id));
        return "/session/only_one_session";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        Session session = sessionService.findById(id);
        model.addAttribute("update_session", session);
        return "/session/update_session";
    }
    @PostMapping("/merge_update/{id}")
    public  String mergeUpdate(@ModelAttribute Session session, @PathVariable long id) {
        sessionService.update(id, session);
        return "redirect:/session/all_sessions";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        sessionService.deleteById(id);
        return "redirect:/session/all_sessions";
    }
}
