package apap.tutorial.manpromanpro.service;

import apap.tutorial.manpromanpro.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer createDeveloper(Developer developer);
    List<Developer> getAllDeveloper();
    Developer getDeveloperById(Long id);
}
