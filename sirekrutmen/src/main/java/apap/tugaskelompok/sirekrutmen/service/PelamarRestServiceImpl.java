package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PelamarRestServiceImpl implements PelamarRestService{
	private WebClient webClient;

	@Autowired
	PelamarDb pelamarDb;
	@Override
	public String aFunction() {
		return "dor";
	}

	@Override
	public List<PelamarModel> retrieveListPelamar(){ return pelamarDb.findAll(); }

	@Override
	public PelamarModel getPelamarById(Long idPelamar) {
		return pelamarDb.findById(idPelamar).get();
	}

	@Override
	public List<PelamarModel> getDaftarPelamar(){
		return pelamarDb.findAll();
	}

	@Override
	public List<PelamarModel> getPelamarInListLamaran(List<LamaranModel> lamaran){
		List<PelamarModel> listDiterima=new ArrayList<PelamarModel>();
		for(int i=0;i<lamaran.size();i++){
			listDiterima.add(lamaran.get(i).getPelamar());
		}
		return listDiterima;
	}
	
}
