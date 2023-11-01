package com.example.springsecuritycinemaproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "cinemas")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String address;
    byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();
}
