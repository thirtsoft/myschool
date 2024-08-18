package com.myschool.sn.dossierEleve.mapping;

import com.myschool.sn.dossierEleve.entity.Eleve;
import com.myschool.sn.dossierEleve.entity.Inscription;
import com.myschool.sn.dossierEleve.entity.Paiement;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;

import javax.inject.Named;

@Named("modelFactoryEl")
public class ModelFactoryDossierEl {

    private final ReferentielService referentielService;

    private final ModelFactoryRef modelFactoryRef;

    public ModelFactoryDossierEl(ReferentielService referentielService, ModelFactoryRef modelFactoryRef) {
        this.referentielService = referentielService;
        this.modelFactoryRef = modelFactoryRef;
    }

    public Eleve createEleve(EleveDTO dto) {
        if (dto == null)
            return null;
        Eleve model = new Eleve();
        model.setId(dto.getId());
        model.setActif(true);
        model.setMatricule(dto.getMatricule());
        model.setPrenom(dto.getPrenom());
        model.setNom(dto.getNom());
        model.setDateNaissance(dto.getDateNaissance());
        model.setSexe(dto.getSexe());
        model.setAdresse(dto.getAdresse());
        model.setLieuNaissance(dto.getLieuNaissance());
        return model;
    }

    public Inscription createInscription(InscriptionDTO dto) {
        if (dto == null)
            return null;
        Inscription model = new Inscription();
        model.setActif(true);
        model.setCreatedBy(dto.getCreatedBy());
        model.setCode(dto.getCode());
        model.setEleve(createEleve(dto.getEleveDTO()));
        model.setAnneeScolaire_debut(modelFactoryRef.createAnneeScolaire(
                referentielService.findAnneeScolaireDTOById(dto.getAnneeScolaireDebutId())
        ));
        model.setAnneeScolaire_fin(modelFactoryRef.createAnneeScolaire(
                referentielService.findAnneeScolaireDTOById(dto.getAnneeScolaireFinId())
        ));
        model.setMontantInscription(dto.getMontantInscription());
        model.setDateInscription(dto.getDateInscription());
        return model;
    }

    public Paiement createPaiement(PaiementDTO dto) {
        if (dto == null)
            return null;
        Paiement model = new Paiement();
        model.setId(dto.getId());
        model.setActif(true);
        model.setCreatedBy(dto.getCreatedBy());
        model.setCode(dto.getCode());
        model.setEleve(createEleve(dto.getEleveDTO()));
        model.setMois(dto.getMois());
        model.setMontant(dto.getMontant());
        model.setDatePaiement(dto.getDatePaiement());
        return model;
    }
}
