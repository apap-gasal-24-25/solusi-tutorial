package apap.tutorial.manpromanpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.manpromanpro.model.Developer;
import apap.tutorial.manpromanpro.repository.DeveloperDb;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperDb developerDb;
    
    @Override
    public Developer addDeveloper(Developer developer) {
        return developerDb.save(developer);
    }

    @Override
    public List<Developer> getAllDeveloper() {
        return developerDb.findAll();
    }

    @Override
    public Developer getDeveloperById(Long idDeveloper) {
        for (Developer developer: getAllDeveloper()) {
            if (developer.getId() == idDeveloper) {
                return developer;
            }
        }
        return null;
    }

    @Override
    public Developer updateDeveloper(Developer developer) {
        Developer getDeveloper = getDeveloperById(developer.getId());
        if (getDeveloper != null) {
            getDeveloper.setNama(developer.getNama());
            getDeveloper.setAlamat(developer.getAlamat());
            getDeveloper.setTanggalBerdiri(developer.getTanggalBerdiri());
            getDeveloper.setEmail(developer.getEmail());
            developerDb.save(getDeveloper);

            return getDeveloper;
        }

        return null;
    }
}
