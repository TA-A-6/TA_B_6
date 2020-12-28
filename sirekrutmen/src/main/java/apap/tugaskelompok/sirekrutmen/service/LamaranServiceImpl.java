package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LamaranServiceImpl implements LamaranService {
	@Autowired
	LamaranDb lamaranDb;

	@Override
	public LamaranModel getLamaranById(Long idLamaran) {
		return lamaranDb.findById(idLamaran).get();
	}

	@Override
	public List<LamaranModel> getLamaranByStatus(Integer status) {
		return lamaranDb.findAllByStatus(status);
	}

	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */
	@Override
	public List<LamaranModel> getStatus(int status) {
		return lamaranDb.findAllByStatus(status);
	}
	@Override
	public LamaranModel ubahStatusLamaran (LamaranModel lamaran){
		LamaranModel lamaranUpdated = lamaranDb.findById(lamaran.getIdLamaran()).get();
		lamaranUpdated.setStatus(lamaran.getStatus());
		lamaranDb.save(lamaranUpdated);
		return lamaranUpdated;
	}

	@Override
	public List<LamaranModel> getLamaranByLowonganAndStatus (LowonganModel lowongan, Integer status){
		return lamaranDb.findLamaranByLowonganAndStatus(lowongan, 2);
	}

	@Override
	public LamaranModel deleteLamaran(LamaranModel lamaran){
		lamaranDb.delete(lamaran);
		return lamaran;
	}

}
