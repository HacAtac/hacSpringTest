package net.hacatac.springboot.controller;

import net.hacatac.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http:localhost:8080/student
    @GetMapping("/student")
    public Student getStudent() {
        Student student = new Student(
            1,
            "John",
            "Doe"
        );
        return student;
    }

    //http:localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe"));
        students.add(new Student(2, "Jane", "Doe"));
        students.add(new Student(3, "Jack", "Doe"));
        students.add(new Student(4, "Jill", "Doe"));
        return students;
    }

    //Spring BOOT REST API with PathVariable
    //{id} - URI Template Variable
    //http:localhost:8080/student/1
    @GetMapping("/students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId) {
        Student student = new Student(
            studentId,
            "John",
            "Doe"
        );
        return student;
    }

    //http://localhost:8080/students/1/ramesh/fadatare
    //{id} - URI Template Variable
    //{firstName} - URI Template Variable
    //{lastName} - URI Template Variable
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable2(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    //Spring boot REST API with RequestParam
    //http://localhost:8080/students/query?id=1&firstName=Jordan&lastName=Henderson
    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    //Spring boot REST API to POST student
    //http://localhost:8080/students/create
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student);

        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        System.out.println(id + " " + firstName + " " + lastName);
        return new Student(id, firstName, lastName);
    }

    //Spring boot REST API to PUT student
    //http://localhost:8080/students/1/update
    @PutMapping("/students/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
        System.out.println(student);
        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        System.out.println(id + " " + firstName + " " + lastName);
        return new Student(id, firstName, lastName);
    }

    //Spring boot REST API to DELETE student
    //http://localhost:8080/students/1/delete
    @DeleteMapping("/students/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println("Student with id " + studentId + " deleted");
        return "Student with id " + studentId + " deleted";
    }

}


