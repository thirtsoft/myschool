package com.myschool.sn.utils.dtos.admin.login;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    private String username;

    private String currentPassword;

    private String newPassword;
}
