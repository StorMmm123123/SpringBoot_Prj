package com.inspirecamp7.myspringbootproject4.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column()
    LocalDateTime createdAt = LocalDateTime.now();
}
