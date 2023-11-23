package org.example.exercicehotel;

import javax.persistence.*;
import java.util.List;
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}