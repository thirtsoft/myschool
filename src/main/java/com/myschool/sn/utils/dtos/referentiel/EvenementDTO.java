package com.myschool.sn.utils.dtos.referentiel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EvenementDTO {

    private Long id;

    private String libelle;

    private Date dateEvenement;

    private String heureDebut;

    private String heureFin;

    private String description;

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
