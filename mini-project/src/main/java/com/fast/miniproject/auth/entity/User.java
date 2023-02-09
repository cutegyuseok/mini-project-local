package com.fast.miniproject.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="members")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name="m_id")
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

    @Column(name="salary")
    private Long salary;

}
