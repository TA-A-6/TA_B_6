package apap.tugaskelompok.sirekrutmen.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.PelamarService;


@Controller
public class PelamarController {
	
	@Autowired
	PelamarService pelamarService;
	
	/*
	  Your code goes here.
	  
	  -Rian
	*/
	
	
//	@GetMapping("/kamar/update/{noKamar}")
//	public String updateKamarFormPage(
//			@PathVariable Long noKamar,
//			Model model
//	) {
//		KamarModel kamar = kamarService.getKamarByNoKamar(noKamar);
//		model.addAttribute("kamar", kamar);
//
//		model.addAttribute("title", "Update Kamar");
//		return "form-update-kamar";
//	}
//	
//	@PostMapping("/kamar/update")
//	public String updateKamarFormSubmit(
//			@ModelAttribute KamarModel kamar,
//			Model model
//	) {
//		kamarService.updateKamar(kamar);
//		model.addAttribute("kamar", kamar);
//		return "update-kamar";
//	}
	
	
	@GetMapping("/pelamar/update/{idPelamar}")
	public String updatePelamarFormPage(
			@PathVariable Long idPelamar,
			Model model
	) {
		
		PelamarModel pelamar = pelamarService.getPelamarById(idPelamar);
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
	

}
