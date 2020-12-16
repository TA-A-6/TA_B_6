package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PelamarServiceImpl implements PelamarService{

	@Autowired
	PelamarDb pelamarDb;
	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */

	@Override
	public PelamarModel getPelamarById(Long idPelamar) {
		
		return pelamarDb.findById(idPelamar).get();
	}

	@Override
	public List<PelamarModel> getDaftarPelamar(){
		return pelamarDb.findAll();
	}

	@Override
	public List<PelamarModel> getPelamarByPelamarId(Integer Id){
		return pelamarDb.findAllByIdPelamar(Id);
	}

}
