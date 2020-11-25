package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;

import javax.transaction.Transactional;

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

}
