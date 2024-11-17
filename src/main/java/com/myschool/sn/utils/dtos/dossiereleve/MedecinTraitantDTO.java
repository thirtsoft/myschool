package com.myschool.sn.utils.dtos.dossiereleve;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MedecinTraitantDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String civilite;
    private String address;
}
