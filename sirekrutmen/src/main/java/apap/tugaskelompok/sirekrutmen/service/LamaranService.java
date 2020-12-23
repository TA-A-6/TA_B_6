package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;

import java.util.List;

public interface LamaranService {
	LamaranModel getLamaranById(Long idLamaran);
	LamaranModel ubahStatusLamaran(LamaranModel lamaran);
	List<LamaranModel> getLamaranByLowonganAndStatus(LowonganModel lowongan, Integer status);
	LamaranModel deleteLamaran(LamaranModel lamaran);
}
