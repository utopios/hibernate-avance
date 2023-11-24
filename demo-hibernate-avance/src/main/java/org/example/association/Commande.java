package org.example.association;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Commande {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL ,fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Article> articles;


}
