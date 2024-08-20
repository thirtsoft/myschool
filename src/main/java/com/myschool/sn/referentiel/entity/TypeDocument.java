package com.myschool.sn.referentiel.entity;

import com.myschool.sn.referentiel.entity.ReferencetielEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "myschool_typedocument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDocument extends ReferencetielEntity {

    @Column(unique = true)
    private String code;

    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        return actif==1;
    }
}
