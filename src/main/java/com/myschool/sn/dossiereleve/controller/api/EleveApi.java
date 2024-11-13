package com.myschool.sn.dossiereleve.controller.api;

import com.myschool.sn.dossiereleve.message.ResponseEleveDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveRequestDTO;
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

@RequestMapping("/eleve")
public interface EleveApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<EleveDTO> getEleves();

    @GetMapping(value = "/{eleveId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EleveDTO getEleve(@PathVariable Long eleveId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEleveDTO createOrUpdateEleve(@RequestBody EleveDTO eleveDTO);

    @PostMapping(value = "/create", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEleveDTO createEleve(@RequestBody EleveDTO eleveRequestDTO);

    @PutMapping(value = "/update/{eleveId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEleveDTO updateEleve(@PathVariable Long eleveId, @RequestBody EleveDTO eleveDTO);

    @GetMapping(value = "/by-matricule/{matricule}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EleveDTO getEleveByMatricule(@PathVariable String matricule);

    @GetMapping(value = "/details/{studentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    DetailsEleveDTO getDetailStudent(@PathVariable Long studentId);

    @GetMapping(value = "/by-nom/{nom}/by-prenom/{prenom}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EleveDTO getEleveByNomOrPrenom(@PathVariable String nom, @PathVariable String prenom);

    @DeleteMapping(value = "/delete/{eleveId}")
    ReponseMessageDTO deleteEleve(@PathVariable Long eleveId);

    @GetMapping(value = "/nbreeleve")
    long countNombreEleve();

}
