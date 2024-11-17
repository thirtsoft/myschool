package com.myschool.sn.dossiereleve.entity;

import com.myschool.sn.referentiel.entity.TypePaiement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "myschool_paiement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "code_paiement")
    private String code;

    @ManyToOne
    @JoinColumn(name = "eleve_uid", referencedColumnName = "id", nullable = false)
    private Eleve eleve;

    private String mois;

    @Column(name = "montant_paiement")
    private Double montant;

    @Column(name = "date_paiement")
    private Date datePaiement;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "typepaiement_par_paiement",
            joinColumns = @JoinColumn(name = "paiement_uid"),
            inverseJoinColumns = @JoinColumn(name = "typepaiement_uid"))
    private Set<TypePaiement> typePaiements;

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
