package com.example.springsecuritycinemaproject.model.entity;

import com.example.springsecuritycinemaproject.model.entity.enums.Genres;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

    @Enumerated(EnumType.STRING)
    Genres genres;

    @Column(name = "created_date")
    LocalDate created;
    String country;
    String language;
    byte[] image;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "movie")
    List<Session> sessions = new ArrayList<>();
}


