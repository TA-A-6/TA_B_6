package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LowonganRestServiceImpl implements LowonganRestService{

	@Autowired
	LowonganDb lowonganDb;

	@Override
	public String aFunction() {
		return "der";
	}

	@Override
	public LowonganModel createLowongan(LowonganModel lowongan) {return lowonganDb.save(lowongan);}

	

//	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa
	
}
