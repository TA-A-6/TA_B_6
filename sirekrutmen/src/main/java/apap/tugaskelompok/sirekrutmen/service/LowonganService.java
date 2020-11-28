package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;

import java.util.List;

public interface LowonganService {
	LowonganModel getLowonganById(Long idLowongan);
	List<LowonganModel> getListLowongan();

}
