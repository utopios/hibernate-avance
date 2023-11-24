package org.example.exercicehotel;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private BigDecimal price;

    @ManyToOne
    private Hotel hotel;


}
