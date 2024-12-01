package com.myschool.sn.utils.dtos.parent;

import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveParentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDetailsDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String address;
    private String profession;
    private String telephone;
    private String email;
    private String civility;
    List<DetailsEleveParentDTO> detailsEleveParentDTOS;
    private boolean active;
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
