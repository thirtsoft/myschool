package com.myschool.sn.parent.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
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

@RequestMapping("/parent")
public interface ParentApi {

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ParentDTO> getAllParents();

    @GetMapping(value = "/{parentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ParentDTO getParent(@PathVariable Long parentId);

    @GetMapping(value = "/utilisateur/{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ParentDTO getParentByUser(@PathVariable Long userId);

    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createOrUpdateParent(@RequestBody ParentDTO parentDTO);

    @PutMapping(value = "/update/{parentId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateParent(@PathVariable Long parentId, @RequestBody ParentDTO parentDTO);

    @DeleteMapping(value = "/delete/{parentId}")
    ReponseMessageDTO deleteParent(@PathVariable Long parentId);
}
