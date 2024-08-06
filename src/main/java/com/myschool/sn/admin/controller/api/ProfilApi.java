package com.myschool.sn.admin.controller.api;

import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api")
public interface ProfilApi {

    @GetMapping(value = "/profiles", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ProfilDTO> getProfils();

    @GetMapping(value = "/profiles/{profileId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfilDTO getProfil(@PathVariable @NotNull Long profileId);

    @PostMapping(value = "/profiles", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createOrUpdateProfil(@RequestBody ProfilDTO profilDTO);

    @DeleteMapping(value = "/profiles/{profilId}")
    ReponseMessageDTO deleteProfil(@PathVariable Long profilId);

    @GetMapping(value = "/profiles/type/{type}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ProfilDTO> getProfilByType(@PathVariable String type);

    @GetMapping(value = "/actions", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ActionDTO> getActions();

    @GetMapping(value = "/actions/{type}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ActionDTO> getActions(@PathVariable String type);
}
