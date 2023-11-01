package com.example.springsecuritycinemaproject.service.impl;

import com.example.springsecuritycinemaproject.exception.EntityNotFoundException;
import com.example.springsecuritycinemaproject.model.entity.Place;
import com.example.springsecuritycinemaproject.model.entity.Room;
import com.example.springsecuritycinemaproject.repository.PlaceRepository;
import com.example.springsecuritycinemaproject.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class PlaceServiceImpl implements ServiceLayer<Place> {

    PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Place with id= %d not found!!!", id)));
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }
    public List<Place>findAllByRoomId(long id){
        return placeRepository.findAllByRoomId(id);
    }

    @Override
    public Place update(Long id, Place place) {
        Place oldPlace = findById(id);
        oldPlace.setX(place.getX());
        oldPlace.setY(place.getY());
        oldPlace.setPrice(place.getPrice());
        oldPlace.setImage(place.getImage());
        placeRepository.save(oldPlace);
        return oldPlace;
    }

    @Override
    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }
}
