package com.myschool.sn.utils.dtos.enseignant;

import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CongesDTO {

    private Long id;

    private String objet;

    private String motif;

    private UtilisateurDTO enseignantDTO;

    private int etat;

    private LocalDate dateDebut;

    private LocalDate dateFin;

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
