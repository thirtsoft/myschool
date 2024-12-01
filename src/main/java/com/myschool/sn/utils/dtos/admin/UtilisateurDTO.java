package com.myschool.sn.utils.dtos.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDTO {

    private Long id;

    private String prenom;

    private String nom;

    private String civility;

    private String address;

    private String telephone;

    private String email;

    private String profession;

    @JsonIgnore
    private String motdepasse;

    private String username;

    @JsonIgnore
    private String activation;

    private ProfilDTO profilDTO;

    private boolean active;

    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        return actif == 1;
    }


}
