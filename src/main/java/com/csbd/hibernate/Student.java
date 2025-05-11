package com.csbd.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "hibernate_gen",
            table = "hibernate_sequences",  // Default table name
            pkColumnName = "sequence_name", // Default column
            valueColumnName = "next_val",   // Default column
            pkColumnValue = "student",      // No "_id" suffix
            allocationSize = 5              // Hibernate cache ids not run select and update query
    )
    // For TABLE if you do not use this default table and column name then you need to use a configuration
    // IDENTITY: use auto-increment column in MySQL
    // TABLE : Use another table then get next ID before insert. Update this next id in this table after insert.
    // AUTO : Choose by Hibernate based on Dialect
    // SEQUENCE : MySQL not supported. Better performance in PostgreSQL. Query once and used cached ids for batch insert.
    private int roll;
    private String name;
    private int age;
}
