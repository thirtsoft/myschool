package com.myschool.sn.dossiereleve.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListeInscriptionDTO;
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

@RequestMapping("/inscription")
public interface InscriptionApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeInscriptionDTO> getInscriptions();

    @GetMapping(value = "/by-annnee-scolaire/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeInscriptionDTO> getInscriptionsByAnneeScolaire(@PathVariable String code);

    @GetMapping(value = "/by-eleve/{eleveId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ListeInscriptionDTO> getInscriptionsByEleveId(@PathVariable Long eleveId);

    @GetMapping(value = "/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    InscriptionDTO getInscription(@PathVariable Long inscriptionId);

    @GetMapping(value = "/details/{inscriptionId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    DetailsInscriptionDTO getDetailsInscription(@PathVariable Long inscriptionId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createInscription(@RequestBody InscriptionDTO inscriptionDTO);

    @PutMapping(value = "/update/{inscriptionId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateInscription(@PathVariable Long inscriptionId, @RequestBody InscriptionDTO inscriptionDTO);

    @GetMapping(value = "/by-nom/{nom}/by-prenom/{prenom}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    DetailsInscriptionDTO getInscriptionByEleve(@PathVariable String nom, @PathVariable String prenom);

    @DeleteMapping(value = "/delete/{inscriptionId}")
    ReponseMessageDTO deleteInscription(@PathVariable Long inscriptionId);
}
