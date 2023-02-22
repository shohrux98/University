//package uz.pdp.PDP.spring.boot.project.controller;
//
//import org.springframework.web.bind.annotation.*;
//import uz.pdp.PDP.spring.boot.project.model.Student;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/")
//public class PersonController {
//
//    List<Student> students = new ArrayList<>(Arrays.asList(new Student(1, "Test", "Testov", new Date(), "+998911234567"),
//            new Student(2, "Test1", "Testov1", new Date(), "+998911234568"),
//            new Student(3, "Test2", "Testov2", new Date(), "+998911234569"),
//            new Student(4, "Test3", "Testov3", new Date(), "+998911234560"),
//            new Student(5, "Test4", "Testov4", new Date(), "+998911234561")
//    ));
//
//    @GetMapping("student/")
//    public List<Student> getStudent(){
//        return students;
//    }
//
//    @GetMapping("student/{id}")
//    public Student getStudentById(@PathVariable Integer id){
//        for (Student student : students) {
//            if (student.getId()==id){
//                return student;
//            }
//        }
//        return new Student();
//    }
//
//    @PostMapping("student/")
//    public String create(@RequestBody Student student){
//        for (Student student1 : students) {
//            if (student1.getPhoneNumber().equals(student.getPhoneNumber())){
//                return "Bunday telefon mavjud";
//            }
//        }
//        students.add(student);
//        return "Successfuly added";
//    }
//
//    @DeleteMapping("delete/{id}")
//    public String deleteStudent(@PathVariable Integer id){
//        Boolean deleted = false;
//        for (Student student : students) {
//            if (student.getId() == id){
//                students.remove(student);
//                deleted = true;
//                break;
//            }
//        }
//        return deleted?"Student deleted":"Student not found";
//    }
//
//    @PutMapping("student/{id}")
//    public String editStudent(@PathVariable Integer id, @RequestBody Student student){
//        boolean edited = false;
//        for (Student s : students) {
//            if (s.getId() == id){
//                s.setFirstName(student.getFirstName());
//                s.setLastName(student.getLastName());
//                s.setBirthDate(student.getBirthDate());
//                s.setPhoneNumber(student.getPhoneNumber());
//                edited = true;
//                break;
//            }
//        }
//        return edited?"Student edited":"Student not found";
//    }
//}