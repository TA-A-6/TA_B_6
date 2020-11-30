package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService{
	
	@Autowired
	LowonganDb lowonganDb;

	@Autowired
	PelamarDb pelamarDb;

	@Autowired
	LamaranDb lamaranDb;
	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */

	@Override
	public LowonganModel getLowonganById(Long idLowongan) {
		
		return lowonganDb.findById(idLowongan).get();
	}

	@Override
	public List<PelamarModel> getDaftarPelamar(LowonganModel lowongan) {
		List<PelamarModel> pelamar = new ArrayList<PelamarModel>();

		List<LamaranModel> listPelamarModel = lowongan.getListLamaran();
		for(LamaranModel nama : listPelamarModel){
			pelamar.add(pelamarDb.findById(nama.getIdLamaran()).get());
		}

		return pelamar;
	}

}
