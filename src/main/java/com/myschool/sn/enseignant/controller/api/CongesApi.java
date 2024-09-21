package com.myschool.sn.enseignant.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
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

@RequestMapping(value = "/conges")
public interface CongesApi {

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CongesDTO> getCongess();

    @GetMapping(value = "/soumis", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CongesDTO> getCongessSoumis();

    @GetMapping(value = "/accepte", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CongesDTO> getCongessAcceptes();

    @GetMapping(value = "/rejete", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CongesDTO> getCongessRejetes();

    @GetMapping(value = "/{congesId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    CongesDTO getConges(@PathVariable Long congesId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createConges(@RequestBody CongesDTO congesDTO);

    @PutMapping(value = "/update/{congesId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateConges(@PathVariable Long congesId, @RequestBody CongesDTO congesDTO);

    @DeleteMapping(value = "/delete/{congesId}")
    ReponseMessageDTO deleteConges(@PathVariable Long congesId);

}
