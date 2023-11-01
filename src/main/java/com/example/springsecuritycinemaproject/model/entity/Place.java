package com.example.springsecuritycinemaproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "places")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int x;
    int y;
    String price;

    @Transient
     long roomId;

    byte[] image;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
     Room room;
}
