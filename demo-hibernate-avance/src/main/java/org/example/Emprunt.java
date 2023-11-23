package org.example;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Livre livre;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;
}
