package com.myschool.sn.utils.dtos.enseignant;

import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursDTO {

    private Long id;

    private String libelle;

    private UtilisateurDTO enseignant;

    private ClasseDTO classe;

    private MatiereDTO matiere;

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
