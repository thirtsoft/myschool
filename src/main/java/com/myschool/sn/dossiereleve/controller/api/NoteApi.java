package com.myschool.sn.dossiereleve.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/note")
public interface NoteApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListNoteDTO> getListNotes();

    @GetMapping(value = "/find/{noteId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    NoteDTO getNote(@PathVariable Long noteId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createNote(@RequestBody NoteDTO noteDTO);

    @PutMapping(value = "/update/{noteId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateNote(@PathVariable Long noteId, @RequestBody NoteDTO noteDTO);

    @GetMapping(value = "/details/{noteId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    DetailsNoteDTO getDetailNote(@PathVariable Long noteId);

    @DeleteMapping(value = "/delete/{noteId}")
    ReponseMessageDTO deleteNote(@PathVariable Long noteId);

    @GetMapping(value = "/byeleve/{eleveId}\"", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListNoteDTO> getListNotesByEleve(@PathVariable Long eleveId);

    @GetMapping(value = "/bymatiere/{matId}\"", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListNoteDTO> getListNotesByMatiere(@PathVariable Long matId);

    @GetMapping(value = "/bysemestre/{semId}\"", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListNoteDTO> getListNotesBySemestre(@PathVariable Long semId);

}
