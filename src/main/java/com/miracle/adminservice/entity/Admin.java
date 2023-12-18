package com.miracle.adminservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 350)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
