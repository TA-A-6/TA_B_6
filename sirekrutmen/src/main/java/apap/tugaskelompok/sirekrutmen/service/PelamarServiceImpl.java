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

	@Override
	public PelamarModel getPelamarById(Long idPelamar) {
		
		return pelamarDb.findById(idPelamar).get();
	}

	@Override
	public Boolean updatePelamar(PelamarModel pelamar) {
		try {
			PelamarModel pelamarFromDb = this.getPelamarById(pelamar.getIdPelamar());
			
			pelamarFromDb.setNama(pelamar.getNama());
			pelamarFromDb.setNoTelepon(pelamar.getNoTelepon());
			pelamarFromDb.setTanggalLahir(pelamar.getTanggalLahir());
			pelamarFromDb.setAlamat(pelamar.getAlamat());
			pelamarFromDb.setTempatLahir(pelamar.getTempatLahir());
			
			pelamarDb.save(pelamarFromDb);
			
			return true;
			
			
		} catch(Exception e) {
			return false;
		}
		
	}

}
