package com.n7.entity;

import com.n7.constant.Gender;
import com.n7.constant.Status;
import com.n7.constant.TimeChoose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String fullName;

    @Column(length = 20)
    private Date dateOfBirth;

    @Column(length = 20)
    private String phone;

    @Column(length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 100)
    private String address;

    @Column
    private Date date;

    @Enumerated(EnumType.STRING)
    private TimeChoose session;

    @Column(length = 255)
    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 255)
    private String note;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private User user;
}
