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
    @Embedded
    private Address address;

    public Student(String name, String enrollmentID, String street, String city, String zipcode) {
        this.name = name;
        this.enrollmentID = enrollmentID;
        this.numberOfCourses = 10;
        this.address = new Address(street, city, zipcode);
    }

    public Student() {
        this.name = null;
//        this.tutor = null;
    }

    @Override
    public String toString() {
        return name + " lives at: " + address.toString();
    }

    public int getId() {
        return id;
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address newAddress) {
        this.address = newAddress;
    }
}
