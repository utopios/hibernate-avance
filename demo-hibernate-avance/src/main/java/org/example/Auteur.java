package org.example;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToMany(mappedBy = "auteurs", fetch = FetchType.LAZY)
    private Set<Livre> livres;
}
