package com.myschool.sn.person;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class Identification extends Personne {

    private String telephone;
    private String email;
    private String civilite;
}
