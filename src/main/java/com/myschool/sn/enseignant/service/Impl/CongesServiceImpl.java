package com.myschool.sn.enseignant.service.Impl;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.enseignant.mapping.CongesMapper;
import com.myschool.sn.enseignant.repository.CongesRepository;
import com.myschool.sn.enseignant.service.CongesService;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCongesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class CongesServiceImpl implements CongesService {

    private final CongesRepository congesRepository;

    private final CongesMapper congesMapper;

    @Override
    public void saveConges(CongesDTO congesDTO) throws EnseignantException {
        if (congesDTO == null) {
            throw new EnseignantException(NULL_OBJECT);
        }
        if (congesDTO.getMotif() == null) {
            throw new EnseignantException("Le motif est obligatoire");
        }
        if (congesDTO.getEnseignantDTO() == null) {
            throw new EnseignantException("Le demandeur est obligatoire");
        }
        if (congesDTO.getDateDebut() == null) {
            throw new EnseignantException("La date de debut doit etre spécifié");
        }
        if (congesDTO.getDateFin() == null) {
            throw new EnseignantException("La date de fin doit etre spécifié");
        }
        var savedConge = congesMapper.toCongesDTO(congesDTO);
        savedConge.setActif(true);
        savedConge.setEtat(1);
        congesRepository.save(savedConge);
    }

    @Override
    public void updateConges(Long id, CongesDTO congesDTO) throws EnseignantException {
        var found = findCongesById(id);
        if (found == null)
            throw new EnseignantException(NOT_FOUND_OBJECT);
        congesDTO.setId(id);
        saveConges(congesDTO);
    }

    @Override
    public CongesDTO findCongesById(Long id) {
        return congesMapper.fromCongesEntity(congesRepository.findCongesById(id));
    }

    @Override
    public List<ListeCongesDTO> findListeConges() {
        return congesRepository.findAll().stream()
                .map(congesMapper::createListConges)
                .toList();
    }

    @Override
    public List<ListeCongesDTO> findListeCongesSoumis() {
        return congesRepository.findAllCongesSoumis().stream()
                .map(congesMapper::createListConges)
                .toList();
    }

    @Override
    public List<ListeCongesDTO> findListeCongesAcceptes() {
        return congesRepository.findAllCongesAcceptes().stream()
                .map(congesMapper::createListConges)
                .toList();
    }

    @Override
    public List<ListeCongesDTO> findListeCongesRejetes() {
        return congesRepository.findAllCongesRejetes().stream()
                .map(congesMapper::createListConges)
                .toList();
    }

    @Override
    public List<ListeCongesDTO> findListeCongesByDemandeur(Long demandeurId) {
        return congesRepository.findCongesByDemandeur(demandeurId).stream()
                .map(congesMapper::createListConges)
                .toList();
    }

    @Override
    public void deleteConges(Long id) {
        var delete = congesRepository.findCongesById(id);
        if (delete.getEtat() != 1) {
            throw new EnseignantException("Impossible de supprimé un congés déjà envoyé ");
        } else {
            delete.setActif(false);
            congesRepository.save(delete);
        }
    }
}
