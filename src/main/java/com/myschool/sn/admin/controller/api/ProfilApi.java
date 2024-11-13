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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/profilage")
public interface ProfilApi {

    @GetMapping(value = "/profile", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ProfilDTO> getProfils();

    @GetMapping(value = "/profile/{profileId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfilDTO getProfil(@PathVariable @NotNull Long profileId);

    @PostMapping(value = "/profile/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createOrUpdateProfil(@RequestBody ProfilDTO profilDTO);


    @PutMapping(value = "/profile/edit/{profileId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO updateProfile(@PathVariable Long profileId, @RequestBody ProfilDTO profilDTO);

    @DeleteMapping(value = "/profile/delete/{profilId}")
    ReponseMessageDTO deleteProfil(@PathVariable Long profilId);

    @GetMapping(value = "/profile/type/{type}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ProfilDTO> getProfilByType(@PathVariable String type);

    //

    @GetMapping(value = "/action/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ActionDTO> gtActions();

    @GetMapping(value = "/action/{type}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ActionDTO> getActions(@PathVariable String type);

    @GetMapping(value = "/action/{actionId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ActionDTO getActionById(@PathVariable @NotNull Long actionId);

    @PostMapping(value = "/action/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createOrUpdateAction(@RequestBody ActionDTO actionDTO);

    @PutMapping(value = "/action/edit/{actionId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO updateAction(@PathVariable Long actionId, @RequestBody ActionDTO actionDTO);

    @DeleteMapping(value = "/action/delete/{actionId}")
    ReponseMessageDTO deleteAction(@PathVariable Long actionId);
}
