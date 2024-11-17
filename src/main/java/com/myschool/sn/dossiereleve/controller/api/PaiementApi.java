package com.myschool.sn.dossiereleve.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementAddDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;
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

@RequestMapping(value = "/paiement")
public interface PaiementApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<PaiementDTO> getPaiements();

    @GetMapping(value = "/by-mois/{mois}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<PaiementDTO> getPaiementsByMois(@PathVariable String mois);

    @GetMapping(value = "/{paiementId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    PaiementDTO getPaiement(@PathVariable @NotNull Long paiementId);

    @PostMapping(value = "/add-pay", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO savePaiement(@RequestBody PaiementAddDTO paiementAddDTO);

    @PutMapping(value = "/update/{paiementId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updatePaiement(@PathVariable Long paiementId, @RequestBody PaiementDTO paiementDTO);

    @GetMapping(value = "/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    PaiementDTO getPaiementByCode(@PathVariable String code);

    @DeleteMapping(value = "/delete/{paiementId}")
    ReponseMessageDTO deletePaiement(@PathVariable Long paiementId);
}
