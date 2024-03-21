package com.n7.dto;

import com.n7.constant.RoleName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @NotBlank(message = "Name of role can not blank")
    private String name;

    @Size(max = 64,message = "Length of description must shorter 64")
    private String description;
}
