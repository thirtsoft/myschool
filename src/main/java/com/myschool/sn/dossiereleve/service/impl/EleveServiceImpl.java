package com.myschool.sn.dossiereleve.service.impl;


import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.admin.service.UserService;
import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.repository.EleveRepository;
import com.myschool.sn.dossiereleve.service.EleveService;
import com.myschool.sn.parent.entity.Parent;
import com.myschool.sn.parent.mapping.ParentMapper;
import com.myschool.sn.parent.repository.ParentRepository;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveRequestDTO;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.ConstantSigs.PASSWORD_PAR_DEFAULT;
import static com.myschool.sn.utils.ConstantSigs.PROFIL_PARENT;
import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
@Transactional
public class EleveServiceImpl implements EleveService {

    private final EleveRepository eleveRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    private final ParentRepository parentRepository;

    private final ParentMapper parentMapper;

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProfilRepository profilRepository;

    private final UserService userService;

    private final ProfilServiceCustom profilServiceCustom;

    private final ModelFactory modelFactory;

    private final DTOFactory dtoFactory;

    @Override
    public Long saveEleve(EleveDTO eleveDTO) throws DossierEleveException {
        if (eleveDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (eleveDTO.getMatricule() == null || eleveDTO.getMatricule().isEmpty())
            throw new DossierEleveException("Le matricule de l'élève est obligatoire");
        if (eleveDTO.getPrenom() == null || eleveDTO.getPrenom().isEmpty())
            throw new DossierEleveException("Le prénom de l'élève est obligatoire");
        if (eleveDTO.getNom() == null || eleveDTO.getNom().isEmpty())
            throw new DossierEleveException("Le nom de l'élève est obligatoire");
        if (eleveDTO.getSexe() == null || eleveDTO.getSexe().isEmpty())
            throw new DossierEleveException("Le sexe de l'élève est obligatoire");
//        if (eleveDTO.getDateNaissance() == null)
//            throw new DossierEleveException("La date de naissance de l'élève est obligatoire");
        Eleve eleveOptional = eleveRepository.findByMatricule(eleveDTO.getMatricule());
        if (eleveDTO.getId() == null && eleveOptional != null
                || (eleveDTO.getId() != null && eleveOptional != null && !eleveOptional.getId().equals(eleveDTO.getId()))) {
            throw new DossierEleveException(String.format("Le matricule %s est déjà associé à un autre élève  .", eleveDTO.getMatricule()));
        }
        Eleve eleve = modelFactoryDossierEl.createEleve(eleveDTO);
        eleve.setActif(true);
        eleveRepository.save(eleve);
        return eleve.getId();
    }

    @Override
    public Long updateEleve(Long id, EleveDTO eleveDTO) throws DossierEleveException {
        EleveDTO eleveDTOUpdate = findEleveById(id);
        if (eleveDTOUpdate == null) {
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        }
        eleveDTO.setId(id);
        saveEleve(eleveDTO);
        return eleveDTO.getId();
    }

    @Override
    public EleveDTO findEleveById(Long id) {
        if (id == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findEleveById(id));
    }

    @Override
    public EleveDTO findByMatricule(String matricule) {
        if (matricule == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findByMatricule(matricule));
    }

    @Override
    public EleveDTO findEleveByNomOrPrenom(String nom, String prenom) {
        if (nom == null && prenom == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findByNomOrPrenom(nom, prenom));
    }

    @Override
    public List<EleveDTO> findAllEleves() {
        return dtoFactoryDossierEl.createListeEleveDTO(eleveRepository.findAllEleves());
    }

    @Override
    public void deleteEleveDTO(Long id) {
        Eleve eleveDelete = eleveRepository.findEleveById(id);
        eleveDelete.setActif(false);
        eleveRepository.save(eleveDelete);
    }

    @Override
    public Long saveEleveRequest(EleveDTO eleveDTO) throws DossierEleveException {
        if (eleveDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (eleveDTO.getMatricule() == null || eleveDTO.getMatricule().isEmpty())
            throw new DossierEleveException("Le matricule de l'élève est obligatoire");
        if (eleveDTO.getPrenom() == null || eleveDTO.getPrenom().isEmpty())
            throw new DossierEleveException("Le prénom de l'élève est obligatoire");
        if (eleveDTO.getNom() == null || eleveDTO.getNom().isEmpty())
            throw new DossierEleveException("Le nom de l'élève est obligatoire");
        if (eleveDTO.getSexe() == null || eleveDTO.getSexe().isEmpty())
            throw new DossierEleveException("Le sexe de l'élève est obligatoire");
        Eleve eleveOptional = eleveRepository.findByMatricule(eleveDTO.getMatricule());
        if (eleveDTO.getId() == null && eleveOptional != null
                || (eleveDTO.getId() != null && eleveOptional != null && !eleveOptional.getId().equals(eleveDTO.getId()))) {
            throw new DossierEleveException(String.format("Le matricule %s est déjà associé à un autre élève  .", eleveDTO.getMatricule()));
        }
        Eleve eleve = modelFactoryDossierEl.createEleve(eleveDTO);
        eleve.setActif(true);
        eleveRepository.save(eleve);
        Utilisateur utilisateur = new Utilisateur();
        List<ParentDTO> utilisateurDTOList = eleveDTO.getParentDTOs();
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        ProfilDTO profilDTO = profilServiceCustom.findProfilById(PROFIL_PARENT);
        for (ParentDTO utilisateurDTORequest : utilisateurDTOList) {
            assert false;
            utilisateurDTO.setUsername(utilisateurDTORequest.getNom());
            utilisateurDTO.setProfilDTO(profilDTO);
            utilisateurDTO.setActif(true);
            utilisateurDTO.setMotdepasse(passwordEncoder.encode(PASSWORD_PAR_DEFAULT));
            utilisateur = modelFactory.createUtilisateur(utilisateurDTO);
            utilisateur.setTelephone(utilisateurDTORequest.getTelephone());
            utilisateur.setEmail(utilisateurDTORequest.getEmail());
            utilisateur.setTypeCompte("Parent");
            utilisateur.setActive(true);
            utilisateurRepository.save(utilisateur);
        }
        return eleve.getId();
    }

    @Override
    public Long savedStudent(EleveDTO eleveDTO) throws DossierEleveException {
        if (eleveDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (eleveDTO.getMatricule() == null || eleveDTO.getMatricule().isEmpty())
            throw new DossierEleveException("Le matricule de l'élève est obligatoire");
        if (eleveDTO.getPrenom() == null || eleveDTO.getPrenom().isEmpty())
            throw new DossierEleveException("Le prénom de l'élève est obligatoire");
        if (eleveDTO.getNom() == null || eleveDTO.getNom().isEmpty())
            throw new DossierEleveException("Le nom de l'élève est obligatoire");
        if (eleveDTO.getSexe() == null || eleveDTO.getSexe().isEmpty())
            throw new DossierEleveException("Le sexe de l'élève est obligatoire");
        Eleve eleveOptional = eleveRepository.findByMatricule(eleveDTO.getMatricule());
        if (eleveDTO.getId() == null && eleveOptional != null
                || (eleveDTO.getId() != null && eleveOptional != null && !eleveOptional.getId().equals(eleveDTO.getId()))) {
            throw new DossierEleveException(String.format("Le matricule %s est déjà associé à un autre élève  .", eleveDTO.getMatricule()));
        }
        Eleve eleve = modelFactoryDossierEl.createEleve(eleveDTO);
        eleve.setActif(true);
        eleveRepository.save(eleve);
        Eleve foundsavedEleve = eleveRepository.findEleveById(eleve.getId());
        for (Utilisateur utilisateur : foundsavedEleve.getUtilisateurs()) {
            utilisateur.setActif(true);
            utilisateur.setMotdepasse(passwordEncoder.encode("Passer123#"));
            utilisateur.setTypeCompte("Parent");
            utilisateurRepository.save(utilisateur);
        }
        return eleve.getId();
    }

    @Override
    public Long updateEleveRequest(Long id, EleveRequestDTO eleveRequestDTO) throws DossierEleveException {
        return 0L;
    }

    @Override
    public DetailsEleveDTO findDetailEleve(Long studentId) {
        if (studentId == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsEleveDTO(eleveRepository.findEleveById(studentId));
    }

    @Override
    public long countNombreEleve() {
        return eleveRepository.countNombreEleve();
    }
}
