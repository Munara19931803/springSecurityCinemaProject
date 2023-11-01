package com.example.springsecuritycinemaproject.repository;

import com.example.springsecuritycinemaproject.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("select s from Movie s join s.sessions r where r.id = :id")
    List<Movie> findAllById(Long id);
}
