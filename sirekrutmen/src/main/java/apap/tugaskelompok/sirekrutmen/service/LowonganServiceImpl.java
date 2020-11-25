package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class LowonganServiceImpl {
	
	@Autowired
	LowonganDb lowonganDb;
	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */

}
