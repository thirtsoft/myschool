package com.myschool.sn.enseignant.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;
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

@RequestMapping(value = "/enseignant")
public interface EnseignantApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<EnseignantDTO> getEnseignants();

    @GetMapping(value = "/{enseignantId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EnseignantDTO getEnseignant(@PathVariable Long enseignantId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createEnseignant(@RequestBody EnseignantDTO enseignantDTO);

    @PutMapping(value = "/update/{enseignantId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateEnseignant(@PathVariable Long enseignantId, @RequestBody EnseignantDTO enseignantDTO);

    @GetMapping(value = "/by-matricule/{matricule}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EnseignantDTO getEnseignantByMatricule(@PathVariable String matricule);

    @GetMapping(value = "/by-telephone/{telephone}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EnseignantDTO getEnseignantByTelephone(@PathVariable String telephone);

    @DeleteMapping(value = "/delete/{enseignantId}")
    ReponseMessageDTO deleteEnseignant(@PathVariable Long enseignantId);

}
