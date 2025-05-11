package com.csbd.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
    public static void main(String[] args) {

        // Have DB connection poll
        // Mapping metadata
        // Caches for optimization
        // Heavy-weight, thread safe
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Student student = new Student();
        student.setName("James Bond");
        student.setAge(21);

        // Hibernate tracks object in the persistence context
        Session session = sessionFactory.openSession();

        // Some transaction will not persist without commit (insert/update)
        Transaction tx = session.beginTransaction();

        // Hibernate queues an insert query
        session.persist(student);

        // Hibernate sends SQL to MySQL, JDBC executes the query
        tx.commit();

        // Hibernate update ID by DB generated ID

        // Object will be detached and no longer be tracked
        session.close();
        System.out.println(student);


    }
}
