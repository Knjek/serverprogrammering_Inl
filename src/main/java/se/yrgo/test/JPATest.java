package se.yrgo.test;

import jakarta.persistence.*;
import se.yrgo.domain.Student;
import se.yrgo.domain.Subject;
import se.yrgo.domain.Tutor;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JPATest {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");
    public static void main(String[] args) {
        setUpData();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Task 1
        System.out.println("\nTask 1:");
        Subject science = em.find(Subject.class, 2);

        TypedQuery<Tutor> query = em.createQuery(
                "from Tutor as tutor where :subject member of tutor.subjectsToTeach", Tutor.class);
        query.setParameter("subject", science);

        List<Tutor> scienceTutors = query.getResultList();

        System.out.println("-Students who's tutor teach science-");
        for (Tutor tutor : scienceTutors) {
            for (Student student : tutor.getTeachingGroup()) {
                System.out.println(student);
            }
        }

        // Task 2
        System.out.println("\nTask 2:");
        Query getStudentAndTeacher = em.createQuery(
                "select student.name, tutor.name from Tutor as tutor join tutor.teachingGroup as student");

        List<Object[]> tutorAndStudent = getStudentAndTeacher.getResultList();

        for (Object[] item : tutorAndStudent) {
            System.out.println("Student: " + item[0] + " ----- Tutor: " + item[1]);
        }

        // Task 3
        System.out.println("\nTask 3:");
        double averageSemesterLength = (Double) em.createQuery(
                "select avg(subject.numberOfSemesters) from Subject as subject").getSingleResult();
        System.out.printf("Average semster length: %.2f%n", averageSemesterLength);

        // Task 4
        System.out.println("\nTask 4:");
        int maxSalary = (Integer) em.createQuery("select max(tutor.salary) from Tutor as tutor").getSingleResult();
        System.out.println("Max salary is: " + maxSalary);

        // Task 5
        System.out.println("\nTask 5:");
        List<Tutor> notSoRichTutors = em.createNamedQuery(
                "salaryGreaterThan", Tutor.class).setParameter("salary", 10000).getResultList();
        for ( Tutor tutor : notSoRichTutors ) {
            System.out.println(tutor.getName() + " has a salary of: " + tutor.getSalary());
        }

        tx.commit();
        em.close();
    }

    public static void setUpData(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        Subject mathematics = new Subject("Mathematics", 2);
        Subject science = new Subject("Science", 2);
        Subject programming = new Subject("Programming", 3);
        em.persist(mathematics);
        em.persist(science);
        em.persist(programming);

        Tutor t1 = new Tutor("ABC123", "Johan Smith", 40000);
        t1.addSubjectsToTeach(mathematics);
        t1.addSubjectsToTeach(science);


        Tutor t2 = new Tutor("DEF456", "Sara Svensson", 20000);
        t2.addSubjectsToTeach(mathematics);
        t2.addSubjectsToTeach(science);

        // This tutor is the only tutor who can teach programming
        Tutor t3 = new Tutor("GHI678", "Karin Lindberg", 0);
        t3.addSubjectsToTeach(programming);

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);

        Student s1 = new Student("Bruce Lee", "2-LEE-2019", "Street 2", "city 2", "2323");
        em.persist(s1);

        t1.createStudentAndAddtoTeachingGroup("Jimi Hendriks", "1-HEN-2019", "Street 1", "city 2", "1212");
        t1.addStudentToTeachingGroup(s1);
//        t2.addStudentToTeachingGroup(s1);
        t3.createStudentAndAddtoTeachingGroup("Roger Waters", "3-WAT-2018", "Street 3", "city 3", "34343");

        tx.commit();
        em.close();
    }

}
