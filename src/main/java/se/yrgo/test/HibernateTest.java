package se.yrgo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import se.yrgo.domain.Student;
import se.yrgo.domain.Tutor;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HibernateTest {
    private static SessionFactory sessionFactory = null;
    public static void main(String[] args) {
        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

//        Tutor newTutor = new Tutor("ABC234", "Natalie Woodward", 387787);
//        Student student1 = new Student("Patrik Howard","1-HOW-2017");
//        Student student2 = new Student("Marie Sani", "2-SAN-2018");
//        Student student3 = new Student("Tom Nikson", "3-NIK-2019");
//        session.save(student1);
//        session.save(student2);
//        session.save(student3);
//        session.save(newTutor);
//        newTutor.addStudentToTeachingGroup(student1);
//        newTutor.addStudentToTeachingGroup(student2);
//        newTutor.addStudentToTeachingGroup(student3);

        Tutor myTutor = (Tutor)session.get(Tutor.class, 4);
        List<Student> students = myTutor.getTeachingGroup();

        for(Student student: students) {
            System.out.println(student);
        }

        tx.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
