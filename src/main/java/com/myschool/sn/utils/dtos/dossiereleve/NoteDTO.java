package com.myschool.sn.utils.dtos.dossiereleve;

import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.SemestreDTO;
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
public class NoteDTO {

    private Long id;

    private EleveDTO eleve;

    private MatiereDTO matiere;

    private SemestreDTO semestre;

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
