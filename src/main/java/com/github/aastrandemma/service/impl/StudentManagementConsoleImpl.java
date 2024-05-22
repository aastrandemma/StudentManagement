package com.github.aastrandemma.service.impl;

import com.github.aastrandemma.data_access.StudentDao;
import com.github.aastrandemma.model.model.Student;
import com.github.aastrandemma.service.StudentManagement;
import com.github.aastrandemma.util.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentManagementConsoleImpl implements StudentManagement {
    private UserInputService scannerService;
    private StudentDao studentDao;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService scannerService, StudentDao studentDao) {
        this.scannerService = scannerService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() {
        int id = getUserInputId();
        String name = getUserInputName();
        return new Student(id, name);
    }

    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student cannot be null.");
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.find(student.getId()));
        if (optionalStudent.isPresent()) throw new IllegalArgumentException("Student already exist.");
        studentDao.save(student);
        return student;
    }

    @Override
    public Student find(int id) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.find(id));
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new IllegalArgumentException("Student not found.");
    }

    @Override
    public Student remove(int id) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.find(id));
        if (optionalStudent.isPresent()) {
            studentDao.delete(id);
            return optionalStudent.get();
        }
        throw new IllegalArgumentException("Student not found.");
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student edit(Student student) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDao.find(student.getId()));
        if (!optionalStudent.isPresent()) throw new IllegalArgumentException("Student doesn't exist.");

        System.out.println("Edit student name for id " + student.getId() + ": ");
        student.setName(scannerService.getString());
        studentDao.delete(student.getId());
        studentDao.save(student);
        return student;
    }

    private String getUserInputName() {
        System.out.println("Enter student name: ");
        return scannerService.getString();
    }

    private int getUserInputId() {
        System.out.println("Enter student id: ");
        return scannerService.getInt();
    }
}