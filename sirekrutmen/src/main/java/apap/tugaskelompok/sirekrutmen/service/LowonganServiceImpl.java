package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService{
	
	@Autowired
	LowonganDb lowonganDb;

	@Override
	public LowonganModel getLowonganById(Long idLowongan) {
		
		return lowonganDb.findById(idLowongan).get();
	}

	@Override
	public List<LowonganModel> getListLowongan() {
		return lowonganDb.findAll();
	}

}
