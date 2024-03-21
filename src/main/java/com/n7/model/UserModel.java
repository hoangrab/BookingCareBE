package com.n7.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel {
    private Long id;
    private String avatar;
    private String fullName;
    private String userName;
    private String phone;
    private String email;
    private String password;
    private Long roleId;
    private boolean enabled;
    private MajorModel major;

    public UserModel(Long id, String avatar, String fullName, String userName,String phone, String email, Long roleId, boolean enabled, MajorModel major) {
        this.id = id;
        this.avatar = avatar;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.enabled = enabled;
        this.major = major;
    }
}
