package apap.tutorial.manpromanpro.service;

import java.util.List;

import apap.tutorial.manpromanpro.model.Pekerja;


public interface PekerjaService {
    Pekerja addPekerja(Pekerja pekerja);
    List<Pekerja> getAllPekerja();
    void deleteListPekerja(List<Pekerja> listPekerja);
}
