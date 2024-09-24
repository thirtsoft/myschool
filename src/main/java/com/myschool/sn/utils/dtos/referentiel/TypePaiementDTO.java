package com.myschool.sn.utils.dtos.referentiel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypePaiementDTO {

    private Long id;
    private String libelle;
}