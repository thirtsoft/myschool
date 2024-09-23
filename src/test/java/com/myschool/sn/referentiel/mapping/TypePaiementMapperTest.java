package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.referentiel.entity.TypePaiement;
import com.myschool.sn.utils.dtos.referentiel.TypePaiementDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TypePaiementMapperTest {


    @Test
    void mapToTypePaiementDTO_shouldMapTypePaiementDto() {
        TypePaiement typePaiement = new TypePaiement(1L, "Mensualite");

        TypePaiementDTO typePaiementDTO = TypePaiementMapper.INSTANCE.mapToTypePaiementDTO(typePaiement);

        assertThat(typePaiementDTO).isNotNull();
        assertThat(typePaiementDTO.getId()).isOne();
        assertThat(typePaiementDTO.getLibelle()).isEqualTo("Mensualite");
    }

    @Test
    void mapToTypePaiementDTO_returnNull_whenEntityIsNull() {

        TypePaiementDTO typePaiementDTO = TypePaiementMapper.INSTANCE.mapToTypePaiementDTO(null);

        assertThat(typePaiementDTO).isNull();
    }

}