package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
@Table (name="STUDENT")
public class Student
{
    private String enrollmentID;
    private String name;
//    @ManyToOne
//    @JoinColumn(name="TUTOR_FK")
//    private Tutor tutor; // This will become a class soon
    @Column (name="NUM_COURSES")
    private Integer numberOfCourses;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)  //This line is optional
    private int id;
//    public Student(String name, Tutor tutor)
//    {
//    	this.name = name;
//    	this.tutor = tutor;
//        this.numberOfCourses = 10;
//    }


    public Student(String name, String enrollmentID)
    {
    	this.name = name;
//    	this.tutor = null;
        this.numberOfCourses = 10;
        this.enrollmentID = enrollmentID;
    }

    public Student() {
        this.name = null;
//        this.tutor = null;
    }

    @Override
    public String toString() {
        return "Student name: " + name;
    }

    public int getId() {
        return id;
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }
//    public void allocateTutor(Tutor tutor) {
//        this.tutor=tutor;
//    }
//
//    public String getTutorName() {
//        return this.tutor.getName();
//    }
//
//    public Tutor getTutor() {
//        return tutor;
//    }
}
