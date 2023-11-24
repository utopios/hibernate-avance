package org.example.association;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Article {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
}
