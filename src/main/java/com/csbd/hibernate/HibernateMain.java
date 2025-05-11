package com.csbd.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateMain {

    // Have DB connection poll
    // Mapping metadata
    // Caches for optimization
    // Heavy-weight, thread safe
    private static final SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Student.class)
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void main(String[] args) {
        createStudent("Jhon Kity", 24);
        createStudent("Err Hood", 25);

        Student student = getStudent(1);
        System.out.println("Read "+ student);

        updateStudent(2, "Error" , 26);
        deleteStudent(1);

        sessionFactory.close();
    }

    private static void createStudent(String name, int age) {
        // Hibernate tracks object in the persistence context
        // It represents a persistence context — meaning it tracks your objects
        // and knows whether they’re new, dirty, or detached.
        try(Session session = sessionFactory.openSession()) {

            // Some transaction will not persist without commit (insert/update)
            session.beginTransaction();

            Student student = new Student(name, age);

            // Hibernate queues an insert query
            // Transaction needed but save do not needed
            // No ID return but save returns ID
            // JPA standard but save Hibernate specific
            session.persist(student);

            // Hibernate sends SQL to MySQL, JDBC executes the query
            session.getTransaction().commit();

            System.out.println(student);

            // Hibernate update ID by DB generated ID

            // Object will be detached and no longer be tracked (finally)
        }
    }
    private static Student getStudent(int id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    private static void updateStudent(int roll, String name, int age) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, roll);
            if(student != null) {
                student.setName(name);
                student.setAge(age);
                // Works for detached or non-detached object
                // Safe use in modern apps
                // This works for object that is connected to another session but update need detached object
                session.merge(student);
            }
            session.getTransaction().commit();

        }
    }

    private static void deleteStudent(int id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if(student != null) {

                // Need attached to session
                // JPA standard but delete Hibernate standard
                session.remove(student);
            }
            session.getTransaction().commit();
        }
    }
}
