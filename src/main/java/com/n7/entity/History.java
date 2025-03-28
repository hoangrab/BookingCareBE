package com.n7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // format: id thuoc: soluong;id thuoc: soluong
    @Column
    private String medicine;

    @Column
    private Date fromDate;

    @Column
    private Date toDate;

    @Column
    private String bhyt;

    @Column
    private String address;

    @Column
    private String name;

    @Column
    private String gender;

    // tinh trang vao vien
    @Column
    private String admissionStatus;

    // tinh trang ra vien
    @Column
    private String dischargeStatus;

    // tom tat benh tinh
    @Column
    private String medicalSummary;
}
