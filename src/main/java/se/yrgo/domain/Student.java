package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
@Table (name="STUDENT")
public class Student
{
    private String enrollmentID;
    private String name;
    @Column (name="NUM_COURSES")
    private Integer numberOfCourses;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)  //This line is optional
    private int id;


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

}
