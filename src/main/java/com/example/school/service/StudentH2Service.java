package com.example.school.service;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.school.model.Student;

import com.example.school.repository.StudentRepository;
import com.example.school.model.StudentRowMapper;
@Service
public class StudentH2Service implements StudentRepository{

@Autowired

public JdbcTemplate db;

      @Override
      public ArrayList<Student> getStudents(){
        
       List<Student> students=db.query("select * from student",new StudentRowMapper());
       ArrayList<Student> allStudents =new ArrayList<>(students);
       return allStudents;
     
       }
 @Override
    public Student getStudentById(int studentId){
       try{
       Student student = db.queryForObject("select * from student where studentId=?",
       new StudentRowMapper(),studentId);
        return student;
       }catch(Exception e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       
    }
 @Override
    public Student addStudent(Student student){
     
     db.update("insert into student (studentName,gender,standard) values(?,?,?)"
      ,student.getStudentName(),student.getGender(),student.getStandard());

      Student existStudent=db.queryForObject("select * from student where studentName=?",
      new StudentRowMapper(),student.getStudentName());

      return getStudentById(existStudent.getStudentId());

    }
@Override
    public String addStudents(ArrayList<Student> students){

     for(int i=0;i<students.size();i++){
      Student student =students.get(i);
     db.update("insert into student (studentName,gender,standard) values(?,?,?)"
      ,student.getStudentName(),student.getGender(),student.getStandard());
     }

      return "Successfully added "+students.size()+" students";

    }


 @Override
    public Student updateStudent(int studentId,Student student){

      Student existStudent=getStudentById(studentId);

      if(student.getStudentName()!=null)
      db.update("update student set studentName=? where studentId = ?",student.getStudentName(),studentId);

      if(student.getGender() !=null)
     db.update("update student set gender=? where studentId= ?",student.getGender(),studentId);

       if(student.getStandard() != 0)
     db.update("update student set standard=? where studentId= ?",student.getStandard(),studentId);
     
     return getStudentById(studentId);
    }

 @Override
    public void deleteStudent(int studentId){

      db.update("delete from student where studentId=?",studentId);

    }
    
}