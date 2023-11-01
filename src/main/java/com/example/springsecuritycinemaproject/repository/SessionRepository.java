package com.example.springsecuritycinemaproject.repository;

import com.example.springsecuritycinemaproject.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    @Query("from Session s where s.movie.id=?1")
    List<Session>findAllByMovieId(long id);
}
