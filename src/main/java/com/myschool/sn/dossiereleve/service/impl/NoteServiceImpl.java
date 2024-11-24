package com.myschool.sn.dossiereleve.service.impl;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.repository.NoteRepository;
import com.myschool.sn.dossiereleve.service.NoteService;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    @Override
    public Long saveNote(NoteDTO noteDTO) throws DossierEleveException {
        if (noteDTO == null) throw new DossierEleveException(NULL_OBJECT);
        if (noteDTO.getEleve() == null)
            throw new DossierEleveException("Le choix d'un élève est obligatoire");
        if (noteDTO.getMatiere() == null)
            throw new DossierEleveException("Le choix de la matière est obligatoire");
        if (noteDTO.getSemestre() == null)
            throw new DossierEleveException("Le choix du semestre est obligatoire");
        if (noteDTO.getNote() == null)
            throw new DossierEleveException("La note associé est obligatoire");
        var savedNote = modelFactoryDossierEl.createNote(noteDTO);
        savedNote.setDateCreation(LocalDateTime.now());
        savedNote.setActif(true);
        noteRepository.save(savedNote);
        return savedNote.getId();
    }

    @Override
    public Long updateNote(Long id, NoteDTO noteDTO) throws DossierEleveException {
        if (findNoteById(id) == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        noteDTO.setId(id);
        saveNote(noteDTO);
        return noteDTO.getId();
    }

    @Override
    public NoteDTO findNoteById(Long id) {
        var searchNote = noteRepository.findById(id).orElseThrow(() ->
                new DossierEleveException(NOT_FOUND_OBJECT));
        return dtoFactoryDossierEl.createNoteDTO(searchNote);
    }

    @Override
    public DetailsNoteDTO findDetailsNote(Long id) {
        var searchNote = noteRepository.findById(id).orElseThrow(() ->
                new DossierEleveException(NOT_FOUND_OBJECT));
        return dtoFactoryDossierEl.createDetailsNoteDTO(searchNote);
    }

    @Override
    public List<ListNoteDTO> findAllListNotes() {
        return noteRepository.findAllNotes().stream()
                .map(dtoFactoryDossierEl::createNoteListDTO)
                .toList();
    }

    @Override
    public List<ListNoteDTO> findAllListNotesByEleve(Long eleveId) {
        return noteRepository.findAllNotesByEleve(eleveId).stream()
                .map(dtoFactoryDossierEl::createNoteListDTO)
                .toList();
    }

    @Override
    public List<ListNoteDTO> findAllListNotesByMatiere(Long matId) {
        return noteRepository.findAllNotesByMatiere(matId).stream()
                .map(dtoFactoryDossierEl::createNoteListDTO)
                .toList();
    }

    @Override
    public List<ListNoteDTO> findAllListNotesSemestre(Long semId) {
        return noteRepository.findAllNotesBySemestre(semId).stream()
                .map(dtoFactoryDossierEl::createNoteListDTO)
                .toList();
    }

    @Override
    public void deleteNote(Long id) {
        var deleted = noteRepository.findById(id).orElseThrow(() ->
                new DossierEleveException(NOT_FOUND_OBJECT));
        deleted.setActif(false);
        noteRepository.save(deleted);
    }
}
