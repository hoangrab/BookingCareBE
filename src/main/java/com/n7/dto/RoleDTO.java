package com.n7.dto;

import com.n7.constant.RoleName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @NotNull(message = "Name of role can not empty")
    private RoleName name;

    @Max(value = 64,message = "Length of description must shorter 64")
    private String description;
}
