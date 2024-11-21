package com.myschool.sn.dossiereleve.mapping;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.entity.Inscription;
import com.myschool.sn.dossiereleve.entity.Note;
import com.myschool.sn.dossiereleve.entity.Paiement;
import com.myschool.sn.dossiereleve.mapping.mapper.MedecinTraitantMapper;
import com.myschool.sn.parent.mapping.ParentMapper;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.referentiel.mapping.TypePaiementMapper;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.myschool.sn.utils.ConstantSigs.PROFIL_PARENT;
import static java.util.Collections.emptyList;

@Named("modelFactoryEl")
@RequiredArgsConstructor
public class ModelFactoryDossierEl {

    private final ModelFactoryRef modelFactoryRef;

    private final TypePaiementMapper typePaiementMapper;

    private final MedecinTraitantMapper medecinTraitantMapper;

    private final ParentMapper parentMapper;

    private final ModelFactory modelFactory;

    private final ProfilServiceCustom profilServiceCustom;

//    private final EleveRepository eleveRepository;

//    private final EleveService eleveService;

    public Eleve createEleve(EleveDTO dto) {
        if (dto == null) {
            return null;
        }
        Eleve model = new Eleve();
        model.setId(dto.getId());
        model.setActif(true);
        model.setMatricule(dto.getMatricule());
        model.setPrenom(dto.getPrenom());
        model.setNom(dto.getNom());
        model.setDateNaissance(dto.getDateNaissance());
        model.setSexe(dto.getSexe());
        model.setAddress(dto.getAddress());
        model.setLieuNaissance(dto.getLieuNaissance());
        model.setAddress(dto.getAddress());
        model.setNationalite(dto.getNationalite());
        model.setAllergies(new HashSet<>(dto.getAllergies()));
        model.setMedecinTraitant(medecinTraitantMapper.toMedecinTraitant(
                dto.getMedecinTraitantDTO()
        ));
        model.setUtilisateurs(createSetParent(
                dto.getUtilisateurDTOS()
        ));
        return model;
    }

    public Set<Utilisateur> createSetParent(List<UtilisateurDTO> utilisateurDTOList) {
        if (utilisateurDTOList == null) return null;
        Set<Utilisateur> parentSet = new HashSet<>();
        for (UtilisateurDTO dto : utilisateurDTOList)
            if (dto != null) parentSet.add(toParent(dto));
        return parentSet;
    }

    public Utilisateur toParent(UtilisateurDTO parentDTO) {
        return Utilisateur.builder()
                .id(parentDTO.getId())
                .prenom(parentDTO.getPrenom())
                .nom(parentDTO.getNom())
                .civility(parentDTO.getCivility())
                .address(parentDTO.getAddress())
                .profession(parentDTO.getProfession())
                .email(parentDTO.getEmail())
                .telephone(parentDTO.getTelephone())
                .username(parentDTO.getUsername())
                .actif(parentDTO.isActif())
                .profil(modelFactory.createProfil(profilServiceCustom.findProfilById(PROFIL_PARENT)))
                .build();
    }


    public List<Eleve> createListEleve(List<EleveDTO> eleveDTOList) {
        if (eleveDTOList == null) {
            return emptyList();
        }
        return eleveDTOList.stream()
                .map(this::createEleve)
                .toList();
    }

    public Inscription createInscription(InscriptionDTO dto) {
        if (dto == null) {
            return null;
        }
        Inscription model = new Inscription();
        model.setActif(true);
        model.setCreatedBy(dto.getCreatedBy());
        model.setCode(dto.getCode());
        model.setEleve(Eleve.builder().id(dto.getEleveDTO().getId()).build());
        model.setClasse(modelFactoryRef.createClasse(dto.getClasseDTO()));
        model.setAnneeScolaire_debut(modelFactoryRef.createAnneeScolaire(dto.getAnneeScolaireDebutDTO()));
        model.setAnneeScolaire_fin(modelFactoryRef.createAnneeScolaire(dto.getAnneeScolaireFinDTO()));
        model.setMontantInscription(dto.getMontantInscription());
        model.setDateInscription(dto.getDateInscription());
        return model;
    }

    public Paiement createPaiement(PaiementDTO dto) {
        if (dto == null) {
            return null;
        }
        Paiement model = new Paiement();
        model.setId(dto.getId());
        model.setActif(true);
        model.setCreatedBy(dto.getCreatedBy());
        model.setCode(dto.getCode());
        model.setEleve(Eleve.builder().id(dto.getEleveDTO().getId()).build());
        model.setMois(dto.getMois());
        model.setMontant(dto.getMontant());
        model.setDatePaiement(dto.getDatePaiement());
        model.setTypePaiements(typePaiementMapper.createSetTypePaiement(dto.getTypePaiements()));
        return model;
    }

    public Note createNote(NoteDTO dto) {
        if (dto == null) return null;
        return Note
                .builder()
                .id(dto.getId())
                .note(dto.getNote())
                .eleve(createEleve(dto.getEleve()))
                .matiere(modelFactoryRef.createMatiere(dto.getMatiere()))
                .semestre(modelFactoryRef.createSemestre(dto.getSemestre()))
                .type(dto.getType())
                .actif(dto.getActif())
                .dateCreation(dto.getDateCreation())
                .build();
    }
}
