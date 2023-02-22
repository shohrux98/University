package uz.pdp.PDP.spring.boot.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false )
    private String firstName;

    @Column(nullable = false )
    private String lastName;

    private Date birthDate;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne
    private Address address;

    @ManyToOne
    private Group group;

    @ManyToMany
    private List<Subject> subjects;
}