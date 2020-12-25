package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.PelamarService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PelamarController {

	@Autowired
	PelamarService pelamarService;
	
	
	@GetMapping("/pelamar/update")
	public String updatePelamarFormPage(
			Model model,
			Authentication auth
	) {
		
		PelamarModel pelamar = pelamarService.getPelamarByUsernameUser(auth.getName());
		model.addAttribute("pelamar", pelamar);
		
		return "update-pelamar-form";
	}
	
	@PostMapping("/pelamar/update")
	public String updatePelamarSubmitForm(
			@ModelAttribute PelamarModel pelamar,
			Model model
	) {
		Boolean condition = pelamarService.updatePelamar(pelamar);
		
		if (condition) {
			model.addAttribute("first_message", "Sukses!");
			model.addAttribute("second_message", "Informasi anda berhasil diubah.");
			
		} else {

			model.addAttribute("first_message", "Gagal!");
			model.addAttribute("second_message", "Mohon maaf, terjadi kesalahan saat mengubah profil anda.");
		}
		
		return "update-pelamar-summary";
	}

	@Autowired
	LamaranService lamaranService;

	@RequestMapping("/pelamar")
	public String listLamaran(Model model) {
		int diterima= 3;

		List<PelamarModel> listPelamar = pelamarService.getDaftarPelamar();
		List<LamaranModel> listLamaran = lamaranService.getStatus(diterima);

		List<PelamarModel> listDiterima=new ArrayList<PelamarModel>();

		for (int i = 0; i <= listLamaran.size()-1; i++) {
			listDiterima.add(listLamaran.get(i).getPelamar());
		}

		model.addAttribute("listDiterima", listDiterima);
		return "viewall-pelamar";
	}
}
