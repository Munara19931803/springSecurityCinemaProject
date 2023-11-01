package com.example.springsecuritycinemaproject.service.impl;

import com.example.springsecuritycinemaproject.exception.EntityNotFoundException;
import com.example.springsecuritycinemaproject.model.entity.Cinema;
import com.example.springsecuritycinemaproject.model.entity.Room;
import com.example.springsecuritycinemaproject.repository.CinemaRepository;
import com.example.springsecuritycinemaproject.repository.RoomRepository;
import com.example.springsecuritycinemaproject.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class RoomServiceImpl implements ServiceLayer<Room> {
    RoomRepository repository;
    CinemaRepository cinemaRepository;

    @Override
    public void save(Room room) {
        Cinema cinema = cinemaRepository.findById(room.getCinemaId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Cinema with id= %d not found!!!", room.getCinemaId())));
        room.setCinema(cinema);
        repository.save(room);
    }

    @Override
    public Room findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("room with id= %d not found!!!", id)));
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll();
    }
    public List<Room>findAllByCinemaId(Long id){
        return (List<Room>) repository.findAllByCinemaId(id);
    }

    @Override
    public Room update(Long id, Room room) {
        Room oldRoom = findById(id);
        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        oldRoom.setImage(room.getImage());
        repository.save(oldRoom);
        return oldRoom;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public List<Room> findAllId(Long id) {
        return repository.findAllByCinemaId(id);
    }
}
