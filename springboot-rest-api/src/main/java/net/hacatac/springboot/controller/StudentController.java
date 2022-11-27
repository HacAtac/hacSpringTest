package net.hacatac.springboot.controller;

import net.hacatac.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //http:localhost:8080/student
    @GetMapping("/student")

    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
            1,
            "John",
            "Doe"
        );
        return ResponseEntity
                .ok()
                .header("Custom-Header", "foo")
                .body(student);
        //return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //http:localhost:8080/students
    @GetMapping
    public ResponseEntity <List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe"));
        students.add(new Student(2, "Jane", "Doe"));
        students.add(new Student(3, "Jack", "Doe"));
        students.add(new Student(4, "Jill", "Doe"));
        return ResponseEntity
                .ok(students);
//                .header("Custom-Header", "foo")
//                .body(students);
    }

    //Spring BOOT REST API with PathVariable
    //{id} - URI Template Variable
    //http:localhost:8080/student/1
    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId) {
        Student student = new Student(
            studentId,
            "John",
            "Doe"
        );
        return ResponseEntity
                .ok(student);

    }

    //http://localhost:8080/students/1/ramesh/fadatare
    //{id} - URI Template Variable
    //{firstName} - URI Template Variable
    //{lastName} - URI Template Variable
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable2(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity
                .ok(student);
    }

    //Spring boot REST API with RequestParam
    //http://localhost:8080/students/query?id=1&firstName=Jordan&lastName=Henderson
    @GetMapping("/query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    //Spring boot REST API to POST student
    //http://localhost:8080/students/create
    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <Student> createStudent(@RequestBody Student student) {
        System.out.println(student);

        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        System.out.println(id + " " + firstName + " " + lastName);
        Student returnStudent = new Student(id, firstName, lastName);
        return new ResponseEntity<>(returnStudent, HttpStatus.CREATED);
    }

    //Spring boot REST API to PUT student
    //http://localhost:8080/students/1/update
    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
        System.out.println(student);
        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        System.out.println(id + " " + firstName + " " + lastName);

        Student updatedStudent = new Student(id, firstName, lastName);
        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    //Spring boot REST API to DELETE student
    //http://localhost:8080/students/1/delete
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println("Student with id " + studentId + " deleted");
        return ResponseEntity
                .ok("Student with id " + studentId + " deleted");
    }

}


