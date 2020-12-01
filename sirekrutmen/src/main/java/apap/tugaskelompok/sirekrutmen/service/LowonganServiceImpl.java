package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService{
	
	@Autowired
	LowonganDb lowonganDb;
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
	public LowonganModel updateLowongan(LowonganModel lowongan) {
		LowonganModel targetLowongan = lowonganDb.findById(lowongan.getIdLowongan()).get();

		try {
			targetLowongan.setDivisi(lowongan.getDivisi());
			targetLowongan.setPosisi(lowongan.getPosisi());
			targetLowongan.setJenisLowongan(lowongan.getJenisLowongan());
			targetLowongan.setUser(lowongan.getUser());
			targetLowongan.setJumlah(lowongan.getJumlah());

			lowonganDb.save(targetLowongan);

			return targetLowongan;
		} catch (NullPointerException nullException) {
			return null;
		}
	}

	@Override
	public LowonganModel updateLowonganVer2(LowonganModel lowongan) {
		LowonganModel targetLowongan = lowonganDb.findById(lowongan.getIdLowongan()).get();

		try {

			targetLowongan.setUser(lowongan.getUser());
			targetLowongan.setJumlah(lowongan.getJumlah());

			lowonganDb.save(targetLowongan);

			return targetLowongan;
		} catch (NullPointerException nullException) {
			return null;
		}
	}

	@Override
	public String getKode (LowonganModel lowongan){

		String kode = lowongan.getDivisi().substring(0,2).toUpperCase() + "-" +
				lowongan.getPosisi().substring(0,2).toUpperCase() + "-" + "0" +
				lowongan.getJenisLowongan().getIdJenisLowongan() + "-";

		Random rand = new Random();
		int upperbond = 99;
		int int_random = rand.nextInt(upperbond);

		kode = kode + Integer.toString(int_random);

		return kode;

	}

}
