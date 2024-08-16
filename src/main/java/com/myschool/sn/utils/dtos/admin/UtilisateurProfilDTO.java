package com.myschool.sn.utils.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurProfilDTO {

    private Long id;

    private String nom;

    private String prenom;

    private String typeProfil;

    private String profil;

    private String email;

    private String telephone;

    private String dateCreation;
}
