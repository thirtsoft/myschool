package com.myschool.sn.utils.dtos.dossiereleve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListNoteDTO {

    private Long id;

    private String eleve;

    private String matiere;

    private String semestre;

    private Double note;

    private String type;

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
