package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name="Person.findById", query = "From Person p WHERE p.id=:id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 10)
    private Address address;

    @PrePersist
    public void prePersist() {

    }

    @PostPersist
    public void postPersist() {

    }

    @PostLoad
    public void postLoad() {

    }
}
