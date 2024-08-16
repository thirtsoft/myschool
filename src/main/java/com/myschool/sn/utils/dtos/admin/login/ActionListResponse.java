package com.myschool.sn.utils.dtos.admin.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionListResponse {
    private String code;
    private String libelle;
}
