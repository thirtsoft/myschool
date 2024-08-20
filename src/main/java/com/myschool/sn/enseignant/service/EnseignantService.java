package com.myschool.sn.enseignant.service;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;

import java.util.List;

public interface EnseignantService {

    /**************   Enseignant ***********************/
    Long saveEnseignant(EnseignantDTO enseignantDTO) throws EnseignantException;

    Long updateEnseignant(Long id, EnseignantDTO enseignantDTO) throws EnseignantException;

    EnseignantDTO findEnseignantDTOById(Long id);

    EnseignantDTO findByMatricule(String matricule);

    EnseignantDTO findByTelephone(String telephone);

    List<EnseignantDTO> findAllEnseignants();

    void deleteEnseignant(Long id);

}
