package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int numberOfSemesters;
    @ManyToMany
    private Set<Tutor> tutors;

    public Subject() {}

    public Subject(String name, int numberOfSemesters) {
        this.name = name;
        this.numberOfSemesters=numberOfSemesters;
        this.tutors = new HashSet<>();
    }

    public void addTutorToSubject(Tutor tutor) {
        tutor.getSubjects().add(this);
    }

    public Set<Tutor> getTutors() {
        return this.tutors;
    }
}
