package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.referentiel.entity.TypePaiement;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.referentiel.TypePaiementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypePaiementMapper {
    TypePaiementMapper INSTANCE = Mappers.getMapper(TypePaiementMapper.class);

    TypePaiementDTO mapToTypePaiementDTO(TypePaiement typePaiement);

    TypePaiement mapToTypePaiement(TypePaiementDTO typePaiementDTO);

    Set<TypePaiement> createSetTypePaiement(List<TypePaiementDTO> typePaiementDTOS);

    List<TypePaiementDTO> createListeTypePaiementDTO(Set<TypePaiement> typePaiements);

}