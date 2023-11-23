package org.example;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur")
    private Set<Emprunt> emprunts;
}
