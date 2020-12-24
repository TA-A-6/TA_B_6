package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

import java.util.List;

public interface PelamarRestService {
	
	String aFunction();

	List<PelamarModel> retrieveListPelamar();

	PelamarModel getPelamarById(Long idPelamar);

	List<PelamarModel> getDaftarPelamar();

	List<PelamarModel> getPelamarInLamaranModel(List<LamaranModel> lamaran);
}
