package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Map<String,Object>> getPelamarInLamaranModel(List<LamaranModel> lamaran){
//		List<PelamarModel> listDiterima=new ArrayList<PelamarModel>();
		List<Map<String,Object>> listDiterima=new ArrayList<>();
		HashMap<String,Object> isi=new HashMap<>();

		for (int i = 0; i <= lamaran.size()-1; i++) {
			isi.put("nama",lamaran.get(i).getPelamar().getNama());
			isi.put("idPelamar", lamaran.get(i).getPelamar().getIdPelamar());
			isi.put("noTelepon", lamaran.get(i).getPelamar().getNoTelepon());
			isi.put("alamat", lamaran.get(i).getPelamar().getAlamat());
			isi.put("divisi", lamaran.get(i).getLowongan().getDivisi());
			listDiterima.add(isi);
		}
		return listDiterima;
	}
	
}
