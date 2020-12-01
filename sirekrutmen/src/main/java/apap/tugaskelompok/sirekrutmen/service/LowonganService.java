package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;

public interface LowonganService {
	LowonganModel getLowonganById(Long idLowongan);
	LowonganModel updateLowongan(LowonganModel lowongan);
	LowonganModel updateLowonganVer2(LowonganModel lowongan);
	String getKode(LowonganModel lowongan);

}
