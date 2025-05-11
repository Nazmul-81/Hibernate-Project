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

        Student student1 = new Student();
        student1.setName("James Bond");
        student1.setAge(21);

        Student student2 = new Student();
        student2.setName("Kail Mayers");
        student2.setAge(18);

        Student student3 = new Student();
        student3.setName("Swift");
        student3.setAge(23);

        // Hibernate tracks object in the persistence context
        Session session = sessionFactory.openSession();

        // Some transaction will not persist without commit (insert/update)
        Transaction tx = session.beginTransaction();

        // Hibernate queues an insert query
        session.persist(student1);
        session.persist(student2);
        session.persist(student3);

        // Hibernate sends SQL to MySQL, JDBC executes the query
        tx.commit();

        // Hibernate update ID by DB generated ID

        // Object will be detached and no longer be tracked
        session.close();
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);


    }
}
