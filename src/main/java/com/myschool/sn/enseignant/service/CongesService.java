package com.myschool.sn.enseignant.service;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCongesDTO;

import java.util.List;

public interface CongesService {

    void saveConges(CongesDTO congesDTO) throws EnseignantException;

    void updateConges(Long id, CongesDTO congesDTO) throws EnseignantException;

    CongesDTO findCongesById(Long id);

    List<ListeCongesDTO> findListeConges();

    List<ListeCongesDTO> findListeCongesSoumis();

    List<ListeCongesDTO> findListeCongesAcceptes();

    List<ListeCongesDTO> findListeCongesRejetes();

    List<ListeCongesDTO> findListeCongesByDemandeur(Long demandeurId);

    void deleteConges(Long id);
}
