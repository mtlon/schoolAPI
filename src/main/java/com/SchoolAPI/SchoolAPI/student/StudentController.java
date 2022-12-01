package com.SchoolAPI.SchoolAPI.student;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> getAll() {
        return studentService.studentList();
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
    @PostMapping("")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    @PutMapping("/{id}/account")
    public String updateAccount(@PathVariable int id, @RequestBody JsonNode data) {
        return studentService.depositMoney(id, data); }

    @PutMapping("/{id}")
    public String updateData(@PathVariable int id, @RequestBody Student updateStudent) {
        studentService.updateStudent(id,updateStudent);
        return "Updated successful";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        studentService.delete(id);
        return "Deleted successful";
    }
}
