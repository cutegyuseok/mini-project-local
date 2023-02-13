package com.fast.miniproject.auth.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name="member_email")
    private String email;

    @Column(name="member_pw")
    private String password;

    @Column(name="member_name")
    private String name;

    @Column(name="member_age")
    private int age;

    @Column(name="member_gender")
    private String gender;

    @Column(name="member_phone")
    private String phone;

    @Column(name="member_salary")
    private Long salary;

    @Column(name = "member_job")
    private String job;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime created_date;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Builder
    public User(String email, String password, String name, int age, String gender, String phone, Long salary, String job) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.salary = salary;
        this.job = job;
    }
}
