package org.example.association;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("voiture")
public class Moto extends Vehicule {
}
