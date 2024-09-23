package com.myschool.sn.dossiereleve.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @ElementCollection
    @CollectionTable(name = "paiement_type", joinColumns = @JoinColumn(name = "id_paiement"))
    @Column(name = "type_paiement")
    private Set<String> typePaiements;

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
