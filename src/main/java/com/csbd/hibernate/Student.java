package com.csbd.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    // IDENTITY: use auto-increment column in MySQL
    // TABLE : Use another table then get next ID before insert. Update this next id in this table after insert.
    // AUTO : Choose by Hibernate based on Dialect
    // SEQUENCE : MySQL not supported. Better performance in PostgreSQL. Query once and used cached ids for batch insert.
    private int roll;
    private String name;
    private int age;
}
