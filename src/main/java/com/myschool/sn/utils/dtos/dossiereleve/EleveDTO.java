package com.myschool.sn.utils.dtos.dossiereleve;

import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
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
public class EleveDTO {

    private Long id;

    private String matricule;

    private String nom;

    private String prenom;

    private String sexe;

    private String lieuNaissance;

    private String nationalite;

    private Date DateNaissance;

    private String address;

    private List<String> allergies;

    private MedecinTraitantDTO medecinTraitantDTO;

    private List<ParentDTO> parentDTOs;

    private List<UtilisateurDTO> utilisateurDTOS;

//    private ParentDTO parentDTO;

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
