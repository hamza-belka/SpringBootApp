package com.example.demo1.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final  StuduentRepository studuentRepository;
    @Autowired
    public StudentService(StuduentRepository studuentRepository) {
        this.studuentRepository = studuentRepository;
    }
    public  void addNewStudent(Student student){
          Optional<Student> studentByEmail= studuentRepository.findStudentByEmail(student.getEmail());
          if (studentByEmail.isPresent()){
              throw new IllegalStateException("email taken");
          }
          studuentRepository.save(student);
        System.out.println(student);
    }

    public List<Student> getStudent(){
        return  studuentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        //studuentRepository.findById(id);
        boolean exist = studuentRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("student with id : " + id + "does not exests");
        }

         studuentRepository.deleteById(id);
    }
   public void deleteStudentX(Student student){
        Optional<Student> std = studuentRepository.findStudentByEmail(student.getEmail());
       System.out.println(std.toString());


       studuentRepository.deleteById(std.get().getId());
}
     @Transactional
     public  void updateStudent(Long id,String name,String email){
           Student student= studuentRepository.findById(id).orElseThrow(()->new IllegalStateException(
                   "student with id : +"+id+"does not exesit"
           ));
           if (name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
               student.setName(name);
           }
         if (email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
             Optional<Student> student1=studuentRepository.findStudentByEmail(email);
             if(student1.isPresent()){
                 throw new IllegalStateException("email taken");
             }
             student.setEmail(email);
         }
   }
}
