package com.myschool.sn.dossierEleve.mapping;

import com.myschool.sn.dossierEleve.entity.Eleve;
import com.myschool.sn.dossierEleve.entity.Inscription;
import com.myschool.sn.dossierEleve.entity.Paiement;
import com.myschool.sn.dossierEleve.repository.EleveRepository;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.dtos.dossierEleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.ListeInscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("dtoFactoryEl")
public class DTOFactoryDossierEl {

    private final DTOFactoryRef dtoFactoryRef;

    private final EleveRepository eleveRepository;

    private final ReferentielService referentielService;

    public DTOFactoryDossierEl(DTOFactoryRef dtoFactoryRef,
                               EleveRepository eleveRepository,
                               ReferentielService referentielService) {
        this.dtoFactoryRef = dtoFactoryRef;
        this.eleveRepository = eleveRepository;
        this.referentielService = referentielService;
    }

    public EleveDTO createEleveDTO(Eleve eleve) {
        if (eleve == null)
            return null;
        EleveDTO dto = new EleveDTO();
        dto.setId(eleve.getId());
        dto.setMatricule(eleve.getMatricule());
        dto.setNom(eleve.getNom());
        dto.setPrenom(eleve.getPrenom());
        dto.setSexe(eleve.getSexe());
        dto.setAdresse(eleve.getAdresse());
        dto.setDateNaissance(eleve.getDateNaissance());
        dto.setLieuNaissance(eleve.getLieuNaissance());
        dto.setActif(eleve.isActif());
        return dto;
    }

    public List<EleveDTO> createListeEleveDTO(List<Eleve> eleves) {
        if (eleves == null)
            return new ArrayList<>();
        List<EleveDTO> dtos = new ArrayList<>();
        for (Eleve eleve : eleves) {
            dtos.add(createEleveDTO(eleve));
        }
        return dtos;
    }

    public InscriptionDTO createInscriptionDTO(Inscription inscription) {
        if (inscription == null)
            return null;
        InscriptionDTO dto = new InscriptionDTO();
        dto.setId(inscription.getId());
        dto.setActif(inscription.isActif());
        dto.setEleveId(inscription.getEleve().getId());
        dto.setAnneeScolaireDebutId(inscription.getAnneeScolaire_debut().getId());
        dto.setAnneeScolaireFinId(inscription.getAnneeScolaire_fin().getId());
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public ListeInscriptionDTO createInscriptionListeDTO(Inscription inscription) {
        if (inscription == null)
            return null;
        ListeInscriptionDTO dto = new ListeInscriptionDTO();
        dto.setId(inscription.getId());
        dto.setActif(inscription.isActif());
        if (inscription.getEleve() != null) {
            Eleve eleve = eleveRepository.findEleveById(inscription.getEleve().getId());
            String nomEleve = eleve.getPrenom() + " " + eleve.getNom();
            dto.setNomCompletEleve(nomEleve);
        }
        if (inscription.getAnneeScolaire_debut() != null) {
            dto.setAnneeScolareDebut(referentielService.findAnneeScolaireDTOById(
                    inscription.getAnneeScolaire_debut().getId()).getLibelle());
        }
        if (inscription.getAnneeScolaire_fin() != null) {
            dto.setAnneeScolareFin(referentielService.findAnneeScolaireDTOById(
                    inscription.getAnneeScolaire_fin().getId()).getLibelle());
        }
        if (inscription.getClasse() != null) {
            dto.setClasse(referentielService.findClasseById(inscription.getClasse().getId()).getLibelle());
        }
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public List<ListeInscriptionDTO> createListeInscriptionDTO(List<Inscription> inscriptions) {
        if (inscriptions == null)
            return new ArrayList<>();
        List<ListeInscriptionDTO> dtos = new ArrayList<>();
        for (Inscription inscription : inscriptions) {
            dtos.add(createInscriptionListeDTO(inscription));
        }
        return dtos;
    }


    public DetailsInscriptionDTO createDetailsInscriptionDTO(Inscription inscription) {
        if (inscription == null)
            return null;
        DetailsInscriptionDTO dto = new DetailsInscriptionDTO();
        dto.setActif(inscription.isActif());
        dto.setId(inscription.getId());
        dto.setEleveDTO(createEleveDTO(inscription.getEleve()));
        dto.setAnneeScolaireDTODebut(dtoFactoryRef.createAnneeScolaireDTO(inscription.getAnneeScolaire_debut()));
        dto.setAnneeScolaireDTOFin(dtoFactoryRef.createAnneeScolaireDTO(inscription.getAnneeScolaire_fin()));
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public List<DetailsInscriptionDTO> createListeDetailsInscriptionDTO(List<Inscription> inscriptions) {
        if (inscriptions == null)
            return new ArrayList<>();
        List<DetailsInscriptionDTO> dtos = new ArrayList<>();
        for (Inscription inscription : inscriptions) {
            dtos.add(createDetailsInscriptionDTO(inscription));
        }
        return dtos;
    }

    /************** Paiement ****************/
    public PaiementDTO createPaiementDTO(Paiement paiement) {
        if (paiement == null)
            return null;
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setCode(paiement.getCode());
        dto.setActif(paiement.isActif());
        dto.setEleveDTO(createEleveDTO(paiement.getEleve()));
        dto.setCreatedBy(paiement.getCreatedBy());
        dto.setMois(paiement.getMois());
        dto.setMontant(paiement.getMontant());
        return dto;
    }

    public List<PaiementDTO> createListePaiementDTO(List<Paiement> paiements) {
        if (paiements == null)
            return new ArrayList<>();
        List<PaiementDTO> dtos = new ArrayList<>();
        for (Paiement paiement : paiements) {
            dtos.add(createPaiementDTO(paiement));
        }
        return dtos;
    }
}
