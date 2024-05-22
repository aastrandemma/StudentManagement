package com.github.aastrandemma.data_access.impl;

import com.github.aastrandemma.data_access.StudentDao;
import com.github.aastrandemma.model.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDaoListImpl implements StudentDao {
    List<Student> students;

    public StudentDaoListImpl() {
        this.students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student find(int id) {
        Optional<Student> findStudent = students.stream()
                .filter(student -> student.getId() == id).
                findFirst();
        return findStudent.orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) {
        Student deleteStudent = find(id);
        if (deleteStudent != null) {
            students.remove(deleteStudent);
        }
    }
}
