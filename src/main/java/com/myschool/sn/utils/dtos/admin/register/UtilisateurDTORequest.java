package com.myschool.sn.utils.dtos.admin.register;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDTORequest {

    private Long id;

    @JsonIgnore
    private String motdepasse;

    private String username;

    private ProfilDTO profilDTO;

    //

    private String nom;

    private String prenom;

    private String address;

    private String telephone;

    private String email;

    private String civility;

    private String profession;

    private UtilisateurDTO utilisateurDTO;

    private String typeParent;

}
