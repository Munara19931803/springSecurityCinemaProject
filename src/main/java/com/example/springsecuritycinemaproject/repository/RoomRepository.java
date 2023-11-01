package com.example.springsecuritycinemaproject.repository;

import com.example.springsecuritycinemaproject.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
   @Query("from Room r where r.cinema.id=:id")
    List<Room>findAllByCinemaId(long id);
}
