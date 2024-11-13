package com.myschool.sn.dossiereleve.mapping.mapper;

import com.myschool.sn.dossiereleve.entity.MedecinTraitant;
import com.myschool.sn.utils.dtos.dossiereleve.MedecinTraitantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedecinTraitantMapper {

    public MedecinTraitant toMedecinTraitant(MedecinTraitantDTO medecinTraitantDTO) {
        return MedecinTraitant.builder()
                .id(medecinTraitantDTO.getId())
                .nom(medecinTraitantDTO.getNom())
                .prenom(medecinTraitantDTO.getPrenom())
                .civilite(medecinTraitantDTO.getCivilite())
                .address(medecinTraitantDTO.getAddress())
                .telephone(medecinTraitantDTO.getTelephone())
                .email(medecinTraitantDTO.getEmail())
                .build();
    }

    public MedecinTraitantDTO toMedecinTraitantDTO(MedecinTraitant medecinTraitant) {
        return MedecinTraitantDTO.builder()
                .id(medecinTraitant.getId())
                .nom(medecinTraitant.getNom())
                .prenom(medecinTraitant.getPrenom())
                .civilite(medecinTraitant.getCivilite())
                .address(medecinTraitant.getAddress())
                .telephone(medecinTraitant.getTelephone())
                .email(medecinTraitant.getEmail())
                .build();
    }

    public List<MedecinTraitantDTO> mapToMedecinTraitantListe(List<MedecinTraitant> medecinTraitants) {
        return medecinTraitants.stream().map(this::toMedecinTraitantDTO).toList();
    }
}
