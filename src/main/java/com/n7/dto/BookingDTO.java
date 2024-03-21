package com.n7.dto;

import com.n7.constant.Gender;
import com.n7.constant.Status;
import com.n7.constant.TimeChoose;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    @NotBlank(message = "Name can not blank")
    private String name;
    @NotBlank(message = "Date of birth can not null")
    private String dob;
    @NotBlank(message = "Phone can not null")
    @Size(min = 9,max = 12,message = "Min of phone must between 9 and 12")
    private String phone;
    @NotBlank(message = "Email can not blank")
    @Email(message = "Invalid format email")
    private String email;
    @NotBlank(message = "Gender can not blank")
    private Gender gender;
    @NotBlank(message = "Address can not blank")
    private String address;
    @NotBlank(message = "Id major can not blank")
    private Long idMajor;
    @NotBlank(message = "Id doctor can not blank")
    private Long idUser;
    @NotBlank(message = "Date can not blank")
    private String date;
    @NotBlank(message = "Time in day can not blank")
    private TimeChoose session;
    @NotBlank(message = "Note can not null")
    @Size(min = 10,max = 255,message = "Length of note must between 10 and 255")
    private String note;
    private String status = "PENDING1";
}
