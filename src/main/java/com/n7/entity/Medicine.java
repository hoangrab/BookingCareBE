package com.n7.entity;

import com.n7.constant.UnitMedicine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "money")
    private Long money;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private UnitMedicine unit;

//    @ManyToOne
//    private TypeMedicine typeMedicine;
}
