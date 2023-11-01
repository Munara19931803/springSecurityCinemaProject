package com.example.springsecuritycinemaproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    LocalDateTime start = LocalDateTime.now();
    int duration;
    LocalDateTime finish;

    byte[] image;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    Movie movie;

    @Transient
    Long movieId;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "sessions")
    List<Room> room= new ArrayList<>();

    @Transient
    Long roomId;
}
