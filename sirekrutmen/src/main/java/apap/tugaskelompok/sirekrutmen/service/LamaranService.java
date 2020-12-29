package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;

import java.util.List;

import java.util.List;

public interface LamaranService {
	LamaranModel getLamaranById(Long idLamaran);


	List<LamaranModel> getLamaranByStatus(Integer status);

	List<LamaranModel> getStatus(int status);


	LamaranModel ubahStatusLamaran(LamaranModel lamaran);
	List<LamaranModel> getLamaranByLowonganAndStatus(LowonganModel lowongan, Integer status);
	LamaranModel deleteLamaran(LamaranModel lamaran);
	List<LamaranModel> getAllLamaranByIdLowongan(Long idLowongan);
}
