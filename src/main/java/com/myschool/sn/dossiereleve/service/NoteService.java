package com.myschool.sn.dossiereleve.service;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;

import java.util.List;

public interface NoteService {

    Long saveNote(NoteDTO noteDTO) throws DossierEleveException;

    Long updateNote(Long id, NoteDTO noteDTO) throws DossierEleveException;

    NoteDTO findNoteById(Long id);

    DetailsNoteDTO findDetailsNote(Long id);

    List<ListNoteDTO> findAllListNotes();

    void deleteNote(Long id);
}
