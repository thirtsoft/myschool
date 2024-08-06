package com.myschool.sn.utils.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilDTO {

    private Long id;

    private String libelle;

    private String typeCompte;

    private List<ActionDTO> actionDTOs;

    private Long createdBy;

}
