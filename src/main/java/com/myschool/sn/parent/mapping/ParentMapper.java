package com.myschool.sn.parent.mapping;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.parent.entity.Parent;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ParentMapper {

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    public Parent toParent(ParentDTO parentDTO) {
        return Parent.builder()
                .id(parentDTO.getId())
                .nom(parentDTO.getNom())
                .prenom(parentDTO.getPrenom())
                .email(parentDTO.getEmail())
                .telephone(parentDTO.getTelephone())
                .typeParent(parentDTO.getTypeParent())
                .actif(parentDTO.getActif())
                //        .eleves(modelFactoryDossierEl.createListEleve(parentDTO.getEleves()))
                .build();
    }

    public List<Parent> mapListParent(List<ParentDTO> parentDTOS) {
        return parentDTOS.stream().map(this::toParent).toList();
    }

    public Set<Parent> createSetParent(List<ParentDTO> parentDTOS) {
        if (parentDTOS == null) return null;
        Set<Parent> parentSet = new HashSet<>();
        for (ParentDTO dto : parentDTOS)
            if (dto != null) parentSet.add(toParent(dto));
        return parentSet;
    }

    public ParentDTO toParentDTO(Parent parent) {
        return ParentDTO.builder()
                .id(parent.getId())
                .nom(parent.getNom())
                .prenom(parent.getPrenom())
                .email(parent.getEmail())
                .telephone(parent.getTelephone())
                .typeParent(parent.getTypeParent())
                .actif(parent.getActif())
                .build();
    }

    public List<ParentDTO> mapListParentDTO(List<Parent> parents) {
        return parents.stream().map(this::toParentDTO).toList();
    }

    public List<ParentDTO> createSetListParentDTO(Set<Parent> list) {
        if (list == null)
            return null;
        List<ParentDTO> dtos = new ArrayList<>();
        for (Parent ins : list) {
            if (ins != null)
                dtos.add(toParentDTO(ins));
        }
        return dtos;
    }

    public ParentListeDTO createParentListeDTO(Utilisateur model) {
        if (model == null) return null;
        ParentListeDTO dto = new ParentListeDTO();
        dto.setId(model.getId());
        dto.setCivility(model.getCivility());
        dto.setAddress(model.getAddress());
        dto.setNomComplet(model.getPrenom() + ' ' + model.getNom());
        dto.setTelephone(model.getTelephone());
        dto.setEmail(model.getEmail());
        dto.setProfession(model.getProfession());
        dto.setActif(model.isActif());
        dto.setActive(model.isActive());
        return dto;
    }

    public ParentDetailsDTO createParentDetailsDTO(Utilisateur model) {
        if (model == null)
            return null;
        ParentDetailsDTO dto = new ParentDetailsDTO();
        dto.setId(model.getId());
        dto.setCivility(model.getCivility());
        dto.setNom(model.getNom());
        dto.setPrenom(model.getPrenom());
        dto.setAddress(model.getAddress());
        dto.setTelephone(model.getTelephone());
        dto.setEmail(model.getEmail());
        dto.setProfession(model.getProfession());
        dto.setActif(model.isActif());
        dto.setDetailsEleveParentDTOS(dtoFactoryDossierEl.createSetDetailsEleveParentDTO(model.getEleves()));
        dto.setActive(model.isActive());
        return dto;
    }

}
