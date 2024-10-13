package com.myschool.sn.utils.dtos.dossiereleve;

import com.myschool.sn.utils.dtos.referentiel.TypePaiementDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {

    private Long id;

    private String code;

    private EleveDTO eleveDTO;

    private String mois;

    private Double montant;

//    private Set<String> typePaiements;

    private List<TypePaiementDTO> typePaiements;

    private Date datePaiement;

    private Long createdBy;

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
