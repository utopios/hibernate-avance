package org.example;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private int anneePublication;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Auteur> auteurs;
}
