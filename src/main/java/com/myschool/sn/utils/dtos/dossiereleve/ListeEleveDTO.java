package com.myschool.sn.utils.dtos.dossiereleve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListeEleveDTO {

    private Long id;

    private String matricule;

    private String nom;

    private String prenom;

    private String sexe;

    private String lieu;

    private Date Date;

    private String adresse;

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
