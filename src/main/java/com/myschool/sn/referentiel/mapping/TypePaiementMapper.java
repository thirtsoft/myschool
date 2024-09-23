package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.referentiel.entity.TypePaiement;
import com.myschool.sn.utils.dtos.referentiel.TypePaiementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypePaiementMapper {
    TypePaiementMapper INSTANCE = Mappers.getMapper(TypePaiementMapper.class);

    TypePaiementDTO mapToTypePaiementDTO(TypePaiement typePaiement);

    TypePaiement mapToTypePaiement(TypePaiementDTO typePaiementDTO);
}