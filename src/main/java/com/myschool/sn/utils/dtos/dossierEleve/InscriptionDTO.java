package com.myschool.sn.utils.dtos.dossierEleve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDTO {

    private Long id;

    private String code;

    private EleveDTO eleveDTO;

    private Long anneeScolaireDebutId;

    private Long anneeScolaireFinId;

    private Double montantInscription;

    private Date dateInscription;

    private Long createdBy;

    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif(int actif) {
        return actif == 1;
    }
}
