package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

import java.util.List;

public interface PelamarService {
	PelamarModel getPelamarById(Long idPelamar);
	Boolean updatePelamar(PelamarModel pelamar);
	
	PelamarModel getPelamarByUsernameUser(String username);

	List<PelamarModel> getDaftarPelamar();

	List<PelamarModel> getPelamarByPelamarId(Integer Id);

}
