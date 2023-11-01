package com.example.springsecuritycinemaproject.service.impl;

import com.example.springsecuritycinemaproject.exception.EntityNotFoundException;
import com.example.springsecuritycinemaproject.model.entity.Movie;
import com.example.springsecuritycinemaproject.model.entity.Session;
import com.example.springsecuritycinemaproject.repository.MovieRepository;
import com.example.springsecuritycinemaproject.repository.SessionRepository;
import com.example.springsecuritycinemaproject.service.ServiceLayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class SessionServiceImpl implements ServiceLayer<Session> {

    SessionRepository sessionRepository;
    MovieRepository movieRepository;
    @Override
    public void save(Session session) {
        Movie movie = movieRepository.findById(session.getMovieId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Movie with id= %d not found!!!",session.getMovieId())));
        session.setMovie(movie);
        sessionRepository.save(session);
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Session with id= %d not found!!!", id)));
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session update(Long id, Session session) {
        Session oldSession = findById(id);
        oldSession.setName(session.getName());
        oldSession.setStart(session.getStart());
        oldSession.setFinish(session.getFinish());
        oldSession.setDuration(session.getDuration());
        oldSession.setImage(session.getImage());
        sessionRepository.save(oldSession);
        return oldSession;

    }

    @Override
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
    public  List<Session>findAllId(long id){
        return sessionRepository.findAllByMovieId(id);
    }
}
