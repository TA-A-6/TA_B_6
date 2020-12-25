package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

public interface PelamarService {
	PelamarModel getPelamarById(Long idPelamar);
	Boolean updatePelamar(PelamarModel pelamar);

}
