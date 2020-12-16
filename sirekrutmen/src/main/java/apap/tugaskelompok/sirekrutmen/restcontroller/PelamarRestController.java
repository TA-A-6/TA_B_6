package apap.tugaskelompok.sirekrutmen.restcontroller;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.LamaranRestService;
import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import apap.tugaskelompok.sirekrutmen.service.PelamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugaskelompok.sirekrutmen.service.PelamarRestService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PelamarRestController {

	@Autowired
	private PelamarRestService pelamarRestService;

	@Autowired
	private LamaranRestService lamaranRestService;


	@RequestMapping(value = "/pelamar")
	private List<LamaranModel> listLamaran(Model model) {

		try{
			int diterima=3;
			List<LamaranModel>  listLamaran = lamaranRestService.getStatus(diterima);
//			List<PelamarModel> listPelamar = pelamarRestService.getDaftarPelamar();

			List<PelamarModel> listPelamar = pelamarRestService.getPelamarInListLamaran(listLamaran);

			return listLamaran;
		}catch (NoSuchElementException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"List Pelamar Not Found"
			);
		}

	}
	
}
