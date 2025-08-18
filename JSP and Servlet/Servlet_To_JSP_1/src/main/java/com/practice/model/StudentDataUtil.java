package com.practice.model;


import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {

    public static List<Student> getStudents() {

        
        List<Student> students = new ArrayList<>();

        
        students.add(new Student("Vivek", "Dhalkari", "vivek@email.com"));
        students.add(new Student("Raj", "Patil", "raj@email.com"));
        students.add(new Student("Sham", "Rao", "sham@email.com"));
        
        return students;
    }
}