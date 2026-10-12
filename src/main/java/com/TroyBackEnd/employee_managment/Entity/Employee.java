package com.TroyBackEnd.employee_managment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstname;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastname;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phonenumber", length = 25)
    private String phonenumber;
    @Column(name = "hireDate", nullable = false)
    private LocalDate hireDate;
    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deparment_id", nullable = false)
    private Department depertment;

}
