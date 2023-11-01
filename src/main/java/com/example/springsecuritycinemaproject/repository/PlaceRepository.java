package com.example.springsecuritycinemaproject.repository;

import com.example.springsecuritycinemaproject.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
   @Query("from Place p where p.room.id=:id")
    List<Place> findAllByRoomId(long id);
}
