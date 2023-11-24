package org.example.association;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;

@Entity
public class Document {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    /*@ManyToOne
    private Person persons;

    //.. société

    //.. evenement*/

    @Any(metaColumn = @Column(name = "entity_type"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
            @MetaValue(value = "P", targetEntity = Person.class)
    })
    @JoinColumn(name = "entity_id")
    private Object associe;
}
