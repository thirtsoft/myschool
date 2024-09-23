package com.myschool.sn.referentiel.service.impl;

import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.entity.Batiment;
import com.myschool.sn.referentiel.entity.CategoryMenu;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Evenement;
import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Meeting;
import com.myschool.sn.referentiel.entity.Menu;
import com.myschool.sn.referentiel.entity.NiveauEducation;
import com.myschool.sn.referentiel.entity.Salle;
import com.myschool.sn.referentiel.entity.Semestre;
import com.myschool.sn.referentiel.entity.TypeDocument;
import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.referentiel.repository.AnneeScolaireRepository;
import com.myschool.sn.referentiel.repository.BatimentRepository;
import com.myschool.sn.referentiel.repository.CategoryMenuRepository;
import com.myschool.sn.referentiel.repository.ClasseRepository;
import com.myschool.sn.referentiel.repository.EvenementRepository;
import com.myschool.sn.referentiel.repository.MatiereRepository;
import com.myschool.sn.referentiel.repository.MeetingRepository;
import com.myschool.sn.referentiel.repository.MenuRepository;
import com.myschool.sn.referentiel.repository.NiveauEducationRepository;
import com.myschool.sn.referentiel.repository.SalleRepository;
import com.myschool.sn.referentiel.repository.SemestreRepository;
import com.myschool.sn.referentiel.repository.TypeDocumentRepository;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.CategoryMenuDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.EvenementDTO;
import com.myschool.sn.utils.dtos.referentiel.ListeClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.MeetingDTO;
import com.myschool.sn.utils.dtos.referentiel.MenuDTO;
import com.myschool.sn.utils.dtos.referentiel.NiveauEducationDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;
import com.myschool.sn.utils.dtos.referentiel.SemestreDTO;
import com.myschool.sn.utils.dtos.referentiel.TypeDocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class ReferentielServiceImpl implements ReferentielService {
    private static final String FORMAT_MESSAGE = "Le libellé %s est déjà associé à un autre batiment .";

    private final AnneeScolaireRepository anneeScolaireRepository;

    private final BatimentRepository batimentRepository;

    private final ClasseRepository classeRepository;

    private final MatiereRepository matiereRepository;

    private final SalleRepository salleRepository;

    private final EvenementRepository evenementRepository;

    private final SemestreRepository semestreRepository;

    private final TypeDocumentRepository typeDocumentRepository;

    private final NiveauEducationRepository niveauEducationRepository;

    private final CategoryMenuRepository categoryMenuRepository;

    private final MenuRepository menuRepository;

    private final MeetingRepository meetingRepository;

    private final DTOFactoryRef dtoFactoryRef;

    private final ModelFactoryRef modelFactoryRef;


    @Override
    public Long saveAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException {
        if (anneeScolaireDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (anneeScolaireDTO.getCode() == null || anneeScolaireDTO.getCode().isEmpty())
            throw new ReferentielException("Le code est obligatoire");
        if (anneeScolaireDTO.getLibelle() == null || anneeScolaireDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle est obligatoire");
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findByCode(anneeScolaireDTO.getCode());
        if (anneeScolaireDTO.getId() == null && anneeScolaire != null
                || (anneeScolaireDTO.getId() != null && anneeScolaire != null && !anneeScolaire.getId().equals(anneeScolaireDTO.getId()))) {
            throw new ReferentielException(String.format("Le code %s est déjà associé à une autre année scolaire  .", anneeScolaireDTO.getCode()));
        }
        AnneeScolaire saveAnneeScolaire = modelFactoryRef.createAnneeScolaire(anneeScolaireDTO);
        saveAnneeScolaire.setActif(true);
        anneeScolaireRepository.save(saveAnneeScolaire);
        return saveAnneeScolaire.getId();
    }

    @Override
    public Long updateAnneeScolaire(Long id, AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException {
        AnneeScolaireDTO anneeScolaireDTODTOUpdate = findAnneeScolaireDTOById(id);
        if (anneeScolaireDTODTOUpdate == null) {
            throw new ReferentielException(NOT_FOUND_OBJECT);
        }
        anneeScolaireDTO.setId(id);
        saveAnneeScolaire(anneeScolaireDTO);
        return anneeScolaireDTO.getId();
    }

    @Override
    public AnneeScolaireDTO findAnneeScolaireDTOById(Long id) {
        if (id == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        return dtoFactoryRef.createAnneeScolaireDTO(anneeScolaireRepository.findAnneeScolaireById(id));
    }

    @Override
    public AnneeScolaireDTO findByCode(String code) {
        if (code == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        return dtoFactoryRef.createAnneeScolaireDTO(anneeScolaireRepository.findByCode(code));
    }

    @Override
    public List<AnneeScolaireDTO> findAllAnneeScolaires() {
        return dtoFactoryRef.createListeAnneeScolaireDTO(anneeScolaireRepository.findAllAnneeScolaires());
    }

    @Override
    public void deleteAnneeScolaire(Long id) {
        AnneeScolaire deleted = anneeScolaireRepository.findAnneeScolaireById(id);
        deleted.setActif(false);
        anneeScolaireRepository.save(deleted);
    }

    @Override
    public Long saveBatiment(BatimentDTO batimentDTO) throws ReferentielException {
        if (batimentDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (batimentDTO.getLibelle() == null || batimentDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé du batiment est obligatoire");
        Batiment foundBatiment = batimentRepository.findByCode(batimentDTO.getLibelle());
        if (batimentDTO.getId() == null && foundBatiment != null
                || (batimentDTO.getId() != null && foundBatiment != null && !foundBatiment.getId().equals(batimentDTO.getId()))) {
            throw new ReferentielException(String.format(FORMAT_MESSAGE, batimentDTO.getLibelle()));
        }
        Batiment savedBatiment = modelFactoryRef.createBatiment(batimentDTO);
        savedBatiment.setActif(true);
        batimentRepository.save(savedBatiment);
        return savedBatiment.getId();
    }

    @Override
    public Long updateBatiment(Long id, BatimentDTO batimentDTO) throws ReferentielException {
        BatimentDTO searchBatimentDTO = findBatimentById(id);
        if (searchBatimentDTO == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        batimentDTO.setId(id);
        saveBatiment(batimentDTO);
        return batimentDTO.getId();
    }

    @Override
    public BatimentDTO findBatimentById(Long id) {
        return dtoFactoryRef.createBatimentDTO(batimentRepository.findBatimentById(id));
    }

    @Override
    public BatimentDTO findBatimentByCode(String code) {
        return dtoFactoryRef.createBatimentDTO(batimentRepository.findByCode(code));
    }

    @Override
    public List<BatimentDTO> findAllBatiments() {
        return dtoFactoryRef.createListeBatimentDTO(batimentRepository.findAllActivesBatiment());
    }

    @Override
    public void deleteBatiment(Long id) {
        Batiment deleted = batimentRepository.findBatimentById(id);
        deleted.setActif(false);
        batimentRepository.save(deleted);
    }

    @Override
    public Long saveClasse(ClasseDTO classeDTO) throws ReferentielException {
        if (classeDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (classeDTO.getLibelle() == null || classeDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle de la classe est obligatoire");
        Classe foundClasse = classeRepository.findClasseByLibelle(classeDTO.getLibelle());
        if (classeDTO.getId() == null && foundClasse != null
                || (classeDTO.getId() != null && foundClasse != null && !foundClasse.getId().equals(classeDTO.getId()))) {
            throw new ReferentielException(String.format(FORMAT_MESSAGE, classeDTO.getLibelle()));
        }
        Classe savedClasse = modelFactoryRef.createClasse(classeDTO);
        savedClasse.setActif(true);
        classeRepository.save(savedClasse);
        return savedClasse.getId();
    }

    @Override
    public Long updateClasse(Long id, ClasseDTO classeDTO) throws ReferentielException {
        ClasseDTO searchClasse = findClasseById(id);
        if (searchClasse == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        classeDTO.setId(id);
        saveClasse(classeDTO);
        return classeDTO.getId();
    }

    @Override
    public ClasseDTO findClasseById(Long id) {
        return dtoFactoryRef.createClasseDTO(classeRepository.findClasseById(id));
    }

    @Override
    public ClasseDTO findClasseByLibelle(String libelle) {
        return dtoFactoryRef.createClasseDTO(classeRepository.findClasseByLibelle(libelle));
    }

    @Override
    public List<ListeClasseDTO> findAllClasses() {
        return dtoFactoryRef.createListClasseDTO(classeRepository.findAllActives());
    }

    @Override
    public void deleteClasse(Long id) {
        Classe deleted = classeRepository.findClasseById(id);
        deleted.setActif(false);
        classeRepository.save(deleted);
    }

    @Override
    public Long saveMatiere(MatiereDTO matiereDTO) throws ReferentielException {
        if (matiereDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (matiereDTO.getCode() == null || matiereDTO.getCode().isEmpty())
            throw new ReferentielException("Le code de la matière est obligatoire");
        if (matiereDTO.getLibelle() == null || matiereDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé de la matière est obligatoire");
        Matiere foundMatiereByCode = matiereRepository.findMatiereByCode(matiereDTO.getCode());
        if (matiereDTO.getId() == null && foundMatiereByCode != null
                || (matiereDTO.getId() != null && foundMatiereByCode != null && !foundMatiereByCode.getId().equals(matiereDTO.getId()))) {
            throw new ReferentielException(String.format("Le code %s est déjà associé à une autre matière  .", matiereDTO.getCode()));
        }
        Matiere foundMatiereByLibelle = matiereRepository.findMatiereByCode(matiereDTO.getCode());
        if (matiereDTO.getId() == null && foundMatiereByLibelle != null
                || (matiereDTO.getId() != null && foundMatiereByLibelle != null && !foundMatiereByLibelle.getId().equals(matiereDTO.getId()))) {
            throw new ReferentielException(String.format(FORMAT_MESSAGE, matiereDTO.getLibelle()));
        }
        Matiere savedMatiere = modelFactoryRef.createMatiere(matiereDTO);
        savedMatiere.setActif(true);
        matiereRepository.save(savedMatiere);
        return savedMatiere.getId();
    }

    @Override
    public Long updateMatiere(Long id, MatiereDTO matiereDTO) throws ReferentielException {
        MatiereDTO searchMatiere = findMatiereById(id);
        if (searchMatiere == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        matiereDTO.setId(id);
        saveMatiere(matiereDTO);
        return matiereDTO.getId();
    }

    @Override
    public MatiereDTO findMatiereById(Long id) {
        return dtoFactoryRef.createMatiereDTO(matiereRepository.findMatiereById(id));
    }

    @Override
    public MatiereDTO findMatiereByCode(String code) {
        return dtoFactoryRef.createMatiereDTO(matiereRepository.findMatiereByCode(code));
    }

    @Override
    public MatiereDTO findMatiereByLibelle(String libelle) {
        return dtoFactoryRef.createMatiereDTO(matiereRepository.findMatiereByLibelle(libelle));
    }

    @Override
    public List<MatiereDTO> findAllMatieres() {
        return dtoFactoryRef.createListeMatiereDTO(matiereRepository.findAllActiveMatieres());
    }

    @Override
    public void deleteMatiere(Long id) {
        Matiere deleted = matiereRepository.findMatiereById(id);
        deleted.setActif(false);
        matiereRepository.save(deleted);
    }

    @Override
    public Long saveSalle(SalleDTO salleDTO) throws ReferentielException {
        if (salleDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (salleDTO.getLibelle() == null || salleDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle de la salle est obligatoire");
        if (salleDTO.getBatimentDTO() == null || salleDTO.getBatimentDTO().getId() == null)
            throw new ReferentielException("L'identifiant du batiment est obligatoire");
        Salle foundSalleByLibelle = salleRepository.findByLibelle(salleDTO.getLibelle());
        if (salleDTO.getId() == null && foundSalleByLibelle != null
                || (salleDTO.getId() != null && foundSalleByLibelle != null && !foundSalleByLibelle.getId().equals(salleDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à une autre salle .", salleDTO.getLibelle())));
        Salle savedSalle = modelFactoryRef.createSalle(salleDTO);
        savedSalle.setActif(true);
        salleRepository.save(savedSalle);
        return savedSalle.getId();
    }

    @Override
    public Long updateSalle(Long id, SalleDTO salleDTO) throws ReferentielException {
        SalleDTO foundSalle = findSalleById(id);
        if (foundSalle == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        salleDTO.setId(id);
        saveSalle(salleDTO);
        return salleDTO.getId();
    }

    @Override
    public SalleDTO findSalleById(Long id) {
        return dtoFactoryRef.createSalleDTO(salleRepository.findSalleById(id));
    }

    @Override
    public SalleDTO findSalleByCode(String code) {
        return dtoFactoryRef.createSalleDTO(salleRepository.findByLibelle(code));
    }

    @Override
    public List<SalleDTO> findAllSalles() {
        return dtoFactoryRef.createListeSalleDTO(salleRepository.findAllActiveSalles());
    }

    @Override
    public List<SalleDTO> findSallesByBatiment(Long batimentId) {
        return dtoFactoryRef.createListeSalleDTO(salleRepository.findSallesByBatiment(batimentId));
    }

    @Override
    public void deleteSalle(Long id) {
        Salle deleted = salleRepository.findSalleById(id);
        deleted.setActif(false);
        salleRepository.save(deleted);
    }

    @Override
    public Long saveEvenement(EvenementDTO evenementDTO) throws ReferentielException {
        if (evenementDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (evenementDTO.getLibelle() == null || evenementDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle de l'événement est obligatoire");
        if (evenementDTO.getHeureDebut() == null || evenementDTO.getHeureDebut().isEmpty())
            throw new ReferentielException("L'heure de début de l'événement est obligatoire");
        if (evenementDTO.getHeureFin() == null || evenementDTO.getHeureFin().isEmpty())
            throw new ReferentielException("L'heure de fin de l'événement est obligatoire");
        Evenement savedEvenement = modelFactoryRef.createEvenement(evenementDTO);
        savedEvenement.setActif(true);
        evenementRepository.save(savedEvenement);
        return savedEvenement.getId();
    }

    @Override
    public Long updateEvenement(Long id, EvenementDTO evenementDTO) throws ReferentielException {
        EvenementDTO foundEvenement = findEvenementById(id);
        if (foundEvenement == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        evenementDTO.setId(id);
        saveEvenement(evenementDTO);
        return evenementDTO.getId();
    }

    @Override
    public EvenementDTO findEvenementById(Long id) {
        return dtoFactoryRef.createEvenementDTO(evenementRepository.findEvenementById(id));
    }

    @Override
    public EvenementDTO findEvenementByLibelle(String libelle) {
        return dtoFactoryRef.createEvenementDTO(evenementRepository.findByLibelle(libelle));
    }

    @Override
    public List<EvenementDTO> findAllEvenements() {
        return dtoFactoryRef.createListeEvenementDTO(evenementRepository.findAllEvenements());
    }

    @Override
    public void deleteEvenement(Long id) {
        Evenement deleted = evenementRepository.findEvenementById(id);
        deleted.setActif(false);
        evenementRepository.save(deleted);
    }

    @Override
    public Long saveSemestre(SemestreDTO semestreDTO) throws ReferentielException {
        if (semestreDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (semestreDTO.getCode() == null || semestreDTO.getCode().isEmpty())
            throw new ReferentielException("Le code du semestre est obligatoire");
        if (semestreDTO.getLibelle() == null || semestreDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle du semestre est obligatoire");
        SemestreDTO semestreByCode = findSemestreByCode(semestreDTO.getCode());
        if (semestreDTO.getId() == null && semestreByCode != null
                || (semestreDTO.getId() != null && semestreByCode != null && !semestreByCode.getId().equals(semestreDTO.getId())))
            throw new ReferentielException((String.format("Le code %s est déjà associé à un autre semestre .", semestreDTO.getCode())));
        SemestreDTO semestreByLibelle = findSemestreByCode(semestreDTO.getCode());
        if (semestreDTO.getId() == null && semestreByLibelle != null
                || (semestreDTO.getId() != null && semestreByLibelle != null && !semestreByLibelle.getId().equals(semestreDTO.getId())))
            throw new ReferentielException((String.format("Le libelle %s est déjà associé à un autre semestre .", semestreDTO.getLibelle())));
        Semestre savedSemestre = modelFactoryRef.createSemestre(semestreDTO);
        savedSemestre.setActif(true);
        semestreRepository.save(savedSemestre);
        return savedSemestre.getId();
    }

    @Override
    public Long updateSemestre(Long id, SemestreDTO semestreDTO) throws ReferentielException {
        SemestreDTO foundSemestre = findSemestreById(id);
        if (foundSemestre == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        semestreDTO.setId(id);
        saveSemestre(semestreDTO);
        return semestreDTO.getId();
    }

    @Override
    public SemestreDTO findSemestreById(Long id) {
        return dtoFactoryRef.createSemestreDTO(semestreRepository.findSemestreById(id));
    }

    @Override
    public SemestreDTO findSemestreByCode(String code) {
        return dtoFactoryRef.createSemestreDTO(semestreRepository.findByCode(code));
    }

    @Override
    public SemestreDTO findSemestreByLibelle(String libelle) {
        return dtoFactoryRef.createSemestreDTO(semestreRepository.findByLibelle(libelle));
    }

    @Override
    public List<SemestreDTO> findAllSemestres() {
        return dtoFactoryRef.createListeSemestreDTO(semestreRepository.findAllSemestres());
    }

    @Override
    public void deleteSemestre(Long id) {
        Semestre deleted = semestreRepository.findSemestreById(id);
        deleted.setActif(false);
        semestreRepository.save(deleted);
    }

    @Override
    public Long saveTypeDocument(TypeDocumentDTO typeDocumentDTO) throws ReferentielException {
        if (typeDocumentDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (typeDocumentDTO.getCode() == null || typeDocumentDTO.getCode().isEmpty())
            throw new ReferentielException("Le code du type de document est obligatoire");
        if (typeDocumentDTO.getLibelle() == null || typeDocumentDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé du type de document est obligatoire");
        TypeDocumentDTO typeDocumentByCode = findTypeDocumentByCode(typeDocumentDTO.getCode());
        if (typeDocumentDTO.getId() == null && typeDocumentByCode != null
                || (typeDocumentDTO.getId() != null && typeDocumentByCode != null && !typeDocumentByCode.getId().equals(typeDocumentDTO.getId())))
            throw new ReferentielException((String.format("Le code %s est déjà associé à un autre type de document .", typeDocumentDTO.getCode())));
        TypeDocumentDTO typeDocumentByLibelle = findTypeDocumentByLibelle(typeDocumentDTO.getLibelle());
        if (typeDocumentDTO.getId() == null && typeDocumentByLibelle != null
                || (typeDocumentDTO.getId() != null && typeDocumentByLibelle != null && !typeDocumentByLibelle.getId().equals(typeDocumentDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à un autre type de document .", typeDocumentDTO.getCode())));
        TypeDocument savedTypeDocument = modelFactoryRef.createTypeDocument(typeDocumentDTO);
        savedTypeDocument.setActif(true);
        return savedTypeDocument.getId();
    }

    @Override
    public Long updateTypeDocument(Long id, TypeDocumentDTO typeDocumentDTO) throws ReferentielException {
        TypeDocumentDTO foundTypeDocument = findTypeDocumentById(id);
        if (foundTypeDocument == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        typeDocumentDTO.setId(id);
        saveTypeDocument(typeDocumentDTO);
        return typeDocumentDTO.getId();
    }

    @Override
    public TypeDocumentDTO findTypeDocumentById(Long id) {
        return dtoFactoryRef.createTypeDocumentDTO(typeDocumentRepository.findTypeDocumentById(id));
    }

    @Override
    public TypeDocumentDTO findTypeDocumentByCode(String code) {
        return dtoFactoryRef.createTypeDocumentDTO(typeDocumentRepository.findByCode(code));
    }

    @Override
    public TypeDocumentDTO findTypeDocumentByLibelle(String libelle) {
        return dtoFactoryRef.createTypeDocumentDTO(typeDocumentRepository.findByLibelle(libelle));
    }

    @Override
    public List<TypeDocumentDTO> findAllTypeDocuments() {
        return dtoFactoryRef.createListeTypeDocumentDTO(typeDocumentRepository.findAllTypeDocuments());
    }

    @Override
    public void deleteTypeDocument(Long id) {
        TypeDocument deleted = typeDocumentRepository.findTypeDocumentById(id);
        deleted.setActif(false);
        typeDocumentRepository.save(deleted);
    }

    @Override
    public Long saveNiveauEducation(NiveauEducationDTO niveauEducationDTO) throws ReferentielException {
        if (niveauEducationDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (niveauEducationDTO.getCode() == null || niveauEducationDTO.getCode().isEmpty())
            throw new ReferentielException("Le code du niveau d'éducation est obligatoire");
        if (niveauEducationDTO.getLibelle() == null || niveauEducationDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé du niveau d'éducation est obligatoire");
        NiveauEducationDTO niveauEducationByCode = findNiveauEducationByCode(niveauEducationDTO.getCode());
        if (niveauEducationDTO.getId() == null && niveauEducationByCode != null
                || (niveauEducationDTO.getId() != null && niveauEducationByCode != null && !niveauEducationByCode.getId().equals(niveauEducationDTO.getId())))
            throw new ReferentielException((String.format("Le code %s est déjà associé à un autre niveau d'éducation .", niveauEducationDTO.getCode())));
        NiveauEducationDTO niveauEducationByLibelle = findNiveauEducationByLibelle(niveauEducationDTO.getLibelle());
        if (niveauEducationDTO.getId() == null && niveauEducationByLibelle != null
                || (niveauEducationDTO.getId() != null && niveauEducationByLibelle != null && !niveauEducationByLibelle.getId().equals(niveauEducationDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à un autre niveau d'éducation .", niveauEducationDTO.getCode())));
        NiveauEducation savedNiveauEducation = modelFactoryRef.createNiveauEducation(niveauEducationDTO);
        savedNiveauEducation.setActif(true);
        niveauEducationRepository.save(savedNiveauEducation);
        return savedNiveauEducation.getId();
    }

    @Override
    public Long updateNiveauEducation(Long id, NiveauEducationDTO niveauEducationDTO) throws ReferentielException {
        NiveauEducationDTO foundNiveauEducation = findNiveauEducationById(id);
        if (foundNiveauEducation == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        niveauEducationDTO.setId(id);
        saveNiveauEducation(niveauEducationDTO);
        return niveauEducationDTO.getId();
    }

    @Override
    public NiveauEducationDTO findNiveauEducationById(Long id) {
        return dtoFactoryRef.createNiveauEducationDTO(niveauEducationRepository.findNiveauEducationById(id));
    }

    @Override
    public NiveauEducationDTO findNiveauEducationByCode(String code) {
        return dtoFactoryRef.createNiveauEducationDTO(niveauEducationRepository.findNiveauEducationByCode(code));
    }

    @Override
    public NiveauEducationDTO findNiveauEducationByLibelle(String libelle) {
        return dtoFactoryRef.createNiveauEducationDTO(niveauEducationRepository.findNiveauEducationByLibelle(libelle));
    }

    @Override
    public List<NiveauEducationDTO> findAllNiveauEducations() {
        return dtoFactoryRef.createListeNiveauEducationDTO(niveauEducationRepository.findAllActiveNiveauEducations());
    }

    @Override
    public void deleteNiveauEducation(Long id) {
        NiveauEducation deleted = niveauEducationRepository.findNiveauEducationById(id);
        deleted.setActif(false);
        niveauEducationRepository.save(deleted);
    }

    @Override
    public Long saveCategoryMenu(CategoryMenuDTO categoryMenuDTO) throws ReferentielException {
        if (categoryMenuDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (categoryMenuDTO.getLibelle() == null || categoryMenuDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé de la catégory du menu est obligatoire");
        CategoryMenu byLibelle = categoryMenuRepository.findByLibelle(categoryMenuDTO.getLibelle());
        if (categoryMenuDTO.getId() == null && byLibelle != null
                || (categoryMenuDTO.getId() != null && byLibelle != null && !byLibelle.getId().equals(categoryMenuDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à un autre catégory de menu .", categoryMenuDTO.getLibelle())));
        CategoryMenu savedCategoryMenu = modelFactoryRef.createCategoryMenu(categoryMenuDTO);
        savedCategoryMenu.setActif(true);
        categoryMenuRepository.save(savedCategoryMenu);
        return savedCategoryMenu.getId();
    }

    @Override
    public Long updateCategoryMenu(Long id, CategoryMenuDTO categoryMenuDTO) throws ReferentielException {
        CategoryMenuDTO foundCategoryMenu = findCategoryMenuById(id);
        if (foundCategoryMenu == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        categoryMenuDTO.setId(id);
        saveCategoryMenu(categoryMenuDTO);
        return categoryMenuDTO.getId();
    }

    @Override
    public CategoryMenuDTO findCategoryMenuById(Long id) {
        return dtoFactoryRef.createCategoryMenuDTO(categoryMenuRepository.findCategoryMenuById(id));
    }

    @Override
    public CategoryMenuDTO findCategoryMenuByLibelle(String libelle) {
        return dtoFactoryRef.createCategoryMenuDTO(categoryMenuRepository.findByLibelle(libelle));
    }

    @Override
    public List<CategoryMenuDTO> findAllCategoryMenus() {
        return dtoFactoryRef.createListeCategoryMenuDTO(categoryMenuRepository.findAllActivesCategoryMenu());
    }

    @Override
    public void deleteCategoryMenu(Long id) {
        CategoryMenu deleted = categoryMenuRepository.findCategoryMenuById(id);
        deleted.setActif(false);
        categoryMenuRepository.save(deleted);
    }

    @Override
    public Long saveMenu(MenuDTO menuDTO) throws ReferentielException {
        if (menuDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (menuDTO.getLibelle() == null || menuDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle du menu est obligatoire");
        if (menuDTO.getCategoryMenuDTO() == null)
            throw new ReferentielException("La category du menu est obligatoire");
        Menu byLibelle = menuRepository.findByLibelle(menuDTO.getLibelle());
        if (menuDTO.getId() == null && byLibelle != null
                || (menuDTO.getId() != null && byLibelle != null && !byLibelle.getId().equals(menuDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à un autre menu .", menuDTO.getLibelle())));
        Menu savedMenu = modelFactoryRef.createMenu(menuDTO);
        savedMenu.setActif(true);
        menuRepository.save(savedMenu);
        return savedMenu.getId();
    }

    @Override
    public Long updateMenu(Long id, MenuDTO menuDTO) throws ReferentielException {
        MenuDTO foundMenu = findMenuById(id);
        if (foundMenu == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        menuDTO.setId(id);
        saveMenu(menuDTO);
        return menuDTO.getId();
    }

    @Override
    public MenuDTO findMenuById(Long id) {
        return dtoFactoryRef.createMenuDTO(menuRepository.findMenuById(id));
    }

    @Override
    public MenuDTO findMenuByLibelle(String libelle) {
        return dtoFactoryRef.createMenuDTO(menuRepository.findByLibelle(libelle));
    }

    @Override
    public List<MenuDTO> findAllMenus() {
        return dtoFactoryRef.createListeMenuDTO(menuRepository.findAllActivesMenu());
    }

    @Override
    public List<MenuDTO> findMenusByCategoryMenu(Long catMenuId) {
        return dtoFactoryRef.createListeMenuDTO(menuRepository.findAllActivesMenu(catMenuId));
    }

    @Override
    public void deleteMenu(Long id) {
        Menu deleted = menuRepository.findMenuById(id);
        deleted.setActif(false);
        menuRepository.save(deleted);
    }

    @Override
    public Long saveMeeting(MeetingDTO meetingDTO) throws ReferentielException {
        if (meetingDTO == null)
            throw new ReferentielException(NULL_OBJECT);
        if (meetingDTO.getLibelle() == null || meetingDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libellé du meeting est obligatoire");
        Meeting byLibelle = meetingRepository.findMeetingByLibelle(meetingDTO.getLibelle());
        if (meetingDTO.getId() == null && byLibelle != null
                || (meetingDTO.getId() != null && byLibelle != null && !byLibelle.getId().equals(meetingDTO.getId())))
            throw new ReferentielException((String.format("Le libellé %s est déjà associé à un autre meeting .", meetingDTO.getLibelle())));
        Meeting savedMeeting = modelFactoryRef.createMeeting(meetingDTO);
        savedMeeting.setActif(true);
        meetingRepository.save(savedMeeting);
        return savedMeeting.getId();
    }

    @Override
    public Long updateMeeting(Long id, MeetingDTO meetingDTO) throws ReferentielException {
        MeetingDTO foundMeeting = findMeetingById(id);
        if (foundMeeting == null)
            throw new ReferentielException(NOT_FOUND_OBJECT);
        meetingDTO.setId(id);
        saveMeeting(meetingDTO);
        return meetingDTO.getId();
    }

    @Override
    public MeetingDTO findMeetingById(Long id) {
        return dtoFactoryRef.createMeetingDTO(meetingRepository.findMeetingById(id));
    }

    @Override
    public MeetingDTO findMeetingByLibelle(String libelle) {
        return dtoFactoryRef.createMeetingDTO(meetingRepository.findMeetingByLibelle(libelle));
    }

    @Override
    public List<MeetingDTO> findAllMeetings() {
        return dtoFactoryRef.createListeMeetingDTO(meetingRepository.findAllActiveMeetings());
    }

    @Override
    public void deleteMeeting(Long id) {
        Meeting deleted = meetingRepository.findMeetingById(id);
        deleted.setActif(false);
        meetingRepository.save(deleted);
    }
}
