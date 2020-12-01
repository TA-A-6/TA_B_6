package apap.tugaskelompok.sirekrutmen.service;


import apap.tugaskelompok.sirekrutmen.model.JenisLowonganModel;
import apap.tugaskelompok.sirekrutmen.repository.JenisLowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisLowonganServiceImpl implements JenisLowonganService {
    @Autowired
    JenisLowonganDb jenisLowonganDb;

    @Override
    public List<JenisLowonganModel> findAll(){return jenisLowonganDb.findAll();}

}
