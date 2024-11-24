package com.myschool.sn.utils.dtos.enseignant;

import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciceDTO {

    private Long id;

    private String libelle;

    private String description;

    private String url;

    private String piece_jointe;

    private UtilisateurDTO enseignant;

    private ClasseDTO classe;

    private Date dateDebut;

    private Date dateFin;

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
