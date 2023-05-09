package se.yrgo.test;

import jakarta.persistence.*;
import se.yrgo.domain.Student;
import se.yrgo.domain.Tutor;

import java.util.List;
import java.util.Set;

public class JPATest {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("databaseConfig");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        Tutor t1 = new Tutor("ABC123", "Teacher 1", 290000);
//        em.persist(t1);
//
//        t1.createStudentAndAddtoTeachingGroup("Eva  Sands", "1-SAN-2019",
//                "street-1", "London", "4455");
//        t1.createStudentAndAddtoTeachingGroup("Sam Everest", "2-EVE-2018",
//                "street-2", "Paris", "6767");

        String requiredName = "sam everest";
        Query q=em.createQuery("FROM Student as student WHERE lower(student.name) =:name", Student.class);
        q.setParameter("name", requiredName);
        List<Student>QueryResult =q.getResultList();
        for(Student st1:QueryResult) {
            System.out.println(st1);
        }


        tx.commit();
        em.close();
    }

}
