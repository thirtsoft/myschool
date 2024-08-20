package com.myschool.sn.enseignant.service;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
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

    /****************    Conges    ******************************/

    Long saveConges(CongesDTO congesDTO) throws EnseignantException;

    Long updateConges(Long id, CongesDTO congesDTO) throws EnseignantException;

    CongesDTO findCongeById(Long id);

    List<CongesDTO> findAllConges();

    List<CongesDTO> findAllCongesSoumis();

    List<CongesDTO> findAllCongesAccepte();

    List<CongesDTO> findAllCongesRejetes();

    void deleteConges(Long id);


}
