package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import apap.tugaskelompok.sirekrutmen.repository.UserDb;

import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PelamarServiceImpl implements PelamarService{

	@Autowired
	PelamarDb pelamarDb;
	
	@Autowired
	UserDb userDb;

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
		
	public List<PelamarModel> getDaftarPelamar(){
		return pelamarDb.findAll();
	}

	@Override
	public List<PelamarModel> getPelamarByPelamarId(Integer Id){
		return pelamarDb.findAllByIdPelamar(Id);

	}

	@Override
	public PelamarModel getPelamarByUsernameUser(String username) {
		String uuid = userDb.findByUsername(username).getUuid();
		return pelamarDb.findByUser_Uuid(uuid);
		
	}

}
