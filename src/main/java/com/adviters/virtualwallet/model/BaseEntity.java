package com.adviters.virtualwallet.model;

import lombok.Getter;

import javax.persistence.*;

//Clase base para generacion de id de los modelos y que hereden de esta

@Entity
@Getter
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
}