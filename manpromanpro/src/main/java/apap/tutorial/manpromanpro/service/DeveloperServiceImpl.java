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
}
