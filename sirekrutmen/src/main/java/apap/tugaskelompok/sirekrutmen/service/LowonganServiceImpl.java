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
import java.util.Random;

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

			//lowonganDb.save(targetLowongan);

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

	@Override
	public String getKodeRest (LowonganModel lowongan, Long idJenis){

		String kode = lowongan.getDivisi().substring(0,2).toUpperCase() + "-" +
				lowongan.getPosisi().substring(0,2).toUpperCase() + "-" + "0" +
				idJenis.toString() + "-";

		Random rand = new Random();
		int upperbond = 99;
		int int_random = rand.nextInt(upperbond);

		kode = kode + Integer.toString(int_random);

		return kode;

	}
	
	@Override
	public List<LowonganModel> getListLowongan() {
		return lowonganDb.findAll();

	}
	
	@Override
	public List<PelamarModel> getDaftarPelamar(LowonganModel lowongan) {
		List<PelamarModel> pelamar = new ArrayList<PelamarModel>();

		List<LamaranModel> listPelamarModel = lowongan.getListLamaran();
		for(LamaranModel nama : listPelamarModel){
			pelamar.add(nama.getPelamar());
		}

		return pelamar;

	}

	@Override
	public void deteleLowongan(LowonganModel lowongan) {
		List<LamaranModel> daftarLamaran = lowongan.getListLamaran();
		for(LamaranModel lamaran: daftarLamaran){
			lamaranDb.delete(lamaran);
		}
		lowonganDb.deleteById(lowongan.getIdLowongan());
	}
}
