package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.model.Developer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Override
    public Developer createDeveloper(Developer developer) {
        return null;
    }

    @Override
    public List<Developer> getAllDeveloper() {
        return List.of();
    }

    @Override
    public Developer getDeveloperById(Long id) {
        return null;
    }
}
