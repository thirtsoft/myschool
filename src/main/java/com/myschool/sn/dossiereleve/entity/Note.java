package com.myschool.sn.dossiereleve.entity;

import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Semestre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "myschool_note")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eleve_uid", referencedColumnName = "id", nullable = false)
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "matiere_uid", referencedColumnName = "id", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "semestre_uid", referencedColumnName = "id", nullable = false)
    private Semestre semestre;

    private Double note;

    private String type;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;


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
