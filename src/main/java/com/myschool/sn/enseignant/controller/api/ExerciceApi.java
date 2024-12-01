package com.myschool.sn.enseignant.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeExerciceDTO;
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

@RequestMapping("/exercice")
public interface ExerciceApi {

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeExerciceDTO> getListeExercices();

    @GetMapping(value = "/find/{exerciceId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ExerciceDTO getExerciceDTO(@PathVariable Long exerciceId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createExercice(@RequestBody ExerciceDTO exerciceDTO);

    @PutMapping(value = "/update/{exerciceId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateEnseignant(@PathVariable Long exerciceId, @RequestBody ExerciceDTO exerciceDTO);

    @GetMapping(value = "/byclasse/{classeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeExerciceDTO> getListeExercicesByClasse(@PathVariable Long classeId);

    @GetMapping(value = "/byenseignant/{enseignantId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeExerciceDTO> getListeExercicesByEnseignant(@PathVariable Long enseignantId);

    @DeleteMapping(value = "/delete/{exerciceId}")
    ReponseMessageDTO deleteExercice(@PathVariable Long exerciceId);
}
