package com.myschool.sn.utils.dtos.enseignant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListeCoursDTO {

    private Long id;

    private String libelle;

    private String enseignant;

    private String classe;

    private String matiere;

    private Date dateDebut;

    private String heureDebut;

    private String heureFin;

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
