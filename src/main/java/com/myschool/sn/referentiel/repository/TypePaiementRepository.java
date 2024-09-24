package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePaiementRepository extends JpaRepository<TypePaiement, Long> {
}