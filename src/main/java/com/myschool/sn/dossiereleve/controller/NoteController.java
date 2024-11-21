package com.myschool.sn.dossiereleve.controller;

import com.myschool.sn.dossiereleve.controller.api.NoteApi;
import com.myschool.sn.dossiereleve.service.NoteService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.EDIT_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
@RequiredArgsConstructor
public class NoteController implements NoteApi {

    private final NoteService noteService;

    @Override
    public List<ListNoteDTO> getListNotes() {
        return noteService.findAllListNotes();
    }

    @Override
    public NoteDTO getNote(Long noteId) {
        return noteService.findNoteById(noteId);
    }

    @Override
    public ReponseMessageDTO createNote(NoteDTO noteDTO) {
        try {
            noteService.saveNote(noteDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateNote(Long noteId, NoteDTO noteDTO) {
        try {
            noteService.updateNote(noteId, noteDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public DetailsNoteDTO getDetailNote(Long noteId) {
        return noteService.findDetailsNote(noteId);
    }

    @Override
    public ReponseMessageDTO deleteNote(Long noteId) {
        try {
            noteService.deleteNote(noteId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
