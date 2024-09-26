package apap.tutorial.manpromanpro.service;

import java.util.List;

import apap.tutorial.manpromanpro.model.Developer;

public interface DeveloperService {
    Developer addDeveloper(Developer developer);
    List<Developer> getAllDeveloper();
    Developer getDeveloperById(Long idDeveloper);
    Developer updateDeveloper(Developer developer);
}
