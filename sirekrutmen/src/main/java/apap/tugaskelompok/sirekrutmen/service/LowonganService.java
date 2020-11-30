package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

import java.util.List;

public interface LowonganService {
	LowonganModel getLowonganById(Long idLowongan);
	List<PelamarModel> getDaftarPelamar(LowonganModel lowongan);

}
