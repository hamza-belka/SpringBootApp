package com.example.demo1.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private  StudentService studentService ;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService ;
    }


   /* @GetMapping
    public List<Student> getStudent(){

        return
    }*/
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
         studentService.addNewStudent(student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
             studentService.deleteStudent(id);
    }

    @DeleteMapping
    public void DeleteTheStudent(@RequestBody Student student){
        studentService.deleteStudentX(student);

    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){

        studentService.updateStudent(studentId,name,email);
    }



}

