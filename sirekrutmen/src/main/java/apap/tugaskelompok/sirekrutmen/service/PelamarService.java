package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

import java.util.List;

public interface PelamarService {
	PelamarModel getPelamarById(Long idPelamar);
	Boolean updatePelamar(PelamarModel pelamar);

	List<PelamarModel> getDaftarPelamar();

	List<PelamarModel> getPelamarByPelamarId(Integer Id);

}
