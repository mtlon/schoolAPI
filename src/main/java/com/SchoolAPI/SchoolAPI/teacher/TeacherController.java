package com.SchoolAPI.SchoolAPI.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public List<Teacher> getAllTeachers() {
        return teacherService.teacherList();
    }
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }
    @PostMapping("")
    public String addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }
    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Teacher updateTeacher) {
        teacherService.updateTeacher(id, updateTeacher);
        return "Teacher updated successful";
    }
    @PutMapping("/{id}/account")
    public String updateAccount(@PathVariable int id) {
        teacherService.updateTeacherAccount(id);
        return "Updated successful";
    }
    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable int id) {
        return teacherService.deleteTeacher(id);
    }
}
