package com.myschool.sn.enseignant.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CoursDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCoursDTO;
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

@RequestMapping("/cours")
public interface CoursApi {

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeCoursDTO> getListCours();

    @GetMapping(value = "/{courId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    CoursDTO getCours(@PathVariable Long courId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createCours(@RequestBody CoursDTO coursDTO);

    @PutMapping(value = "/update/{courId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateCours(@PathVariable Long courId, @RequestBody CoursDTO coursDTO);

    @DeleteMapping(value = "/delete/{courId}")
    ReponseMessageDTO deleteCours(@PathVariable Long courId);

    @GetMapping(value = "/byclasse/{classeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeCoursDTO> getListCoursByClasse(@PathVariable Long classeId);

    @GetMapping(value = "/bymatiere/{matId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeCoursDTO> getListCoursByMatiere(@PathVariable Long matId);

    @GetMapping(value = "/byenseignant/{ensId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeCoursDTO> getListCoursByEnseignant(@PathVariable Long ensId);
}
