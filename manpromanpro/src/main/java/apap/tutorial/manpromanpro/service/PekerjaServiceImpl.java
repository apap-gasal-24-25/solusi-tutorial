package apap.tutorial.manpromanpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.repository.PekerjaDb;
import jakarta.transaction.Transactional;

@Service
public class PekerjaServiceImpl implements PekerjaService{
    
    @Autowired
    PekerjaDb pekerjaDb;

    @Override
    public Pekerja addPekerja(Pekerja pekerja) {
        return pekerjaDb.save(pekerja);
    }

    @Override
    public List<Pekerja> getAllPekerja() {
        return pekerjaDb.findAll(Sort.by(Sort.Order.asc("nama").ignoreCase()));
    }

    @Override
    @Transactional
    public void deleteListPekerja(List<Pekerja> listPekerja){
        System.out.println(listPekerja);
        pekerjaDb.deleteAll(listPekerja);
    }

}
