package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.rest.LowonganDto;

public interface LowonganRestService {
	
	String aFunction();

	LowonganModel createLowongan(LowonganModel lowongan);


}
