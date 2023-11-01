package com.example.springsecuritycinemaproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "rooms")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    double rating;

    byte[] image;


    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Transient
    private long cinemaId;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", fetch = FetchType.LAZY)
    private List<Place> places= new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "rooms_and_sessions",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Session> sessions = new ArrayList<>();
}

