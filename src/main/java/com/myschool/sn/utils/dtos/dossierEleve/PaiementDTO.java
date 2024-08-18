package com.myschool.sn.utils.dtos.dossierEleve;

import com.myschool.sn.dossierEleve.entity.Eleve;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
        return actif==1;
    }

}
