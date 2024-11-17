package com.myschool.sn.dossiereleve.entity;

import com.myschool.sn.person.Identification;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "myschool_medecin_traitant")
@Getter
@Setter
@SuperBuilder
public class MedecinTraitant extends Identification {

    public MedecinTraitant() {

    }
}
