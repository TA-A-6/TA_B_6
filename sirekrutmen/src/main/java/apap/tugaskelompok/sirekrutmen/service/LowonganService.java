package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

import java.util.List;

import java.util.List;

public interface LowonganService {
	LowonganModel getLowonganById(Long idLowongan);

	LowonganModel updateLowongan(LowonganModel lowongan);
	LowonganModel updateLowonganVer2(LowonganModel lowongan);
	String getKode(LowonganModel lowongan);
	List<LowonganModel> getListLowongan();
	List<PelamarModel> getDaftarPelamar(LowonganModel lowongan);
	String getKodeRest (LowonganModel lowongan, Long idJenis);

}
