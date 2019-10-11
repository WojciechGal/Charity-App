package pl.coderslab.charity.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public void saveInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Institution findInstitutionById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public Long checkNumberOfSupportedInstitutions() {
        return institutionRepository.countInstitutionsByDonationsNotNull();
    }
}
