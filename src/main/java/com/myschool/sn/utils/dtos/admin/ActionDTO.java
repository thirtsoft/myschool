package com.myschool.sn.utils.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActionDTO {

    private Long id;

    private String code;

    private String libelle;

    private Long module;

    private String typeCompte;
}
