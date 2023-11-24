package org.example.hibernateannotation;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@FilterDef(name="ageFilter", parameters = @ParamDef(name="minAge", type="int"))
@Filters( {
        @Filter(name = "ageFilter", condition = "age > :minAge")
    }
)

@NamedQueries(
        @NamedQuery(name = "Person.findAll", query = "FROM Person  p")
)
public class Person {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @NaturalId
    private String email;

    private int age;


    @Formula("UPPER(name)")
    private String nameUppercase;



}
