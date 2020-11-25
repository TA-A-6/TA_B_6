package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class LamaranServiceImpl implements LamaranService{
	@Autowired 
	LamaranDb lamaranDb;

	@Override
	public LamaranModel getLamaranById(Long idLamaran) {
		
		return lamaranDb.findById(idLamaran).get();
	}
	
	
	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */
}
