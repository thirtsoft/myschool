package com.myschool.sn.referentiel.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import org.antlr.v4.runtime.misc.NotNull;
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

@RequestMapping(value = "/api/referentiel")
public interface ReferentielApi {

    @GetMapping(value = "/anneescolaire", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<AnneeScolaireDTO> getAnneeScolaires();

    @GetMapping(value = "/anneescolaire/{anneeScolaireId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    AnneeScolaireDTO getAnneeScolaire(@PathVariable @NotNull Long anneeScolaireId);

    @PostMapping(value = "/anneescolaire/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createAnneeScolaire(@RequestBody AnneeScolaireDTO anneeScolaireDTO);

    @PutMapping(value = "/anneescolaire/update/{anneeScolaireId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateAnneeScolaire(@PathVariable Long anneeScolaireId, @RequestBody AnneeScolaireDTO anneeScolaireDTO);

    @GetMapping(value = "/anneescolaire/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    AnneeScolaireDTO getAnneeScolaireByCode(@PathVariable String code);

    @DeleteMapping(value = "/anneescolaire/delete/{eleveId}")
    ReponseMessageDTO deleteAnneeScolaire(@PathVariable Long anneeScolaireId);

}
