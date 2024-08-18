package com.myschool.sn.referentiel.service;

import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;

import java.util.List;

public interface ReferentielService {

    /**************   AnneeScolaire ***********************/
    Long saveAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException;

    Long updateAnneeScolaire(Long id, AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException;

    AnneeScolaireDTO findAnneeScolaireDTOById(Long id);

    AnneeScolaireDTO findByCode(String code);

    List<AnneeScolaireDTO> findAllAnneeScolaires();

    void deleteAnneeScolaire(Long id);
}
