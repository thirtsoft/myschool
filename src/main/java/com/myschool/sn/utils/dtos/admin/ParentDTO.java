package com.myschool.sn.utils.dtos.admin;

import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentDTO {

    private Long id;

    private String prenom;

    private String nom;

    private String civility;

    private String address;

    private String telephone;

    private String email;

    private String profession;

    private String username;

    private ProfilDTO profilDTO;

    private List<EleveDTO> detailsEleveDTOS;

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
