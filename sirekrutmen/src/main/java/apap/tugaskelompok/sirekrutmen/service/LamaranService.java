package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;

import java.util.List;

public interface LamaranService {
	LamaranModel getLamaranById(Long idLamaran);

	List<LamaranModel> getLamaranByStatus(Integer status);

	List<LamaranModel> getStatus(int status);

}
