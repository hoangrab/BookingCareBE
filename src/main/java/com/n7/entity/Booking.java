package com.n7.entity;

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

    @OneToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    private User user;
}
