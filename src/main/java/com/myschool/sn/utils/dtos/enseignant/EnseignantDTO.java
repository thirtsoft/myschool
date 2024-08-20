package com.myschool.sn.utils.dtos.enseignant;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantDTO {

    private Long id;

    private String matricule;

    private String nom;

    private String prenom;

    private String cni;

    private String civilite;

    private String email;

    private String adresse;

    private String telephone;

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
