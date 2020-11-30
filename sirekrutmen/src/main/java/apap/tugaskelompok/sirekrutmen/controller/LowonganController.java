package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaskelompok.sirekrutmen.service.LowonganService;

import java.util.List;

@Controller
public class LowonganController {
	@Autowired
	LowonganService lowonganService;
	
	
	@GetMapping("/home")
	private String home(Model model) {
		return "home";
	}

	@RequestMapping("/lowongan")
	public String listLowongan(Model model){

		List<LowonganModel> listLowongan = lowonganService.getListLowongan();

		model.addAttribute("listLowongan", listLowongan);
		return "viewall-lowongan";
	}
	
	@GetMapping("/lowongan/detail/{id}")
	public String detailLowongan(
			@PathVariable(value="id") Long id,
			Model model
	){
		LowonganModel lowongan = lowonganService.getLowonganById(id);
		List<PelamarModel> daftarPelamar = lowonganService.getDaftarPelamar(lowongan);
		model.addAttribute("lowongan",lowongan);
		model.addAttribute("pelamar",daftarPelamar);
		model.addAttribute("jenisLowongan",lowongan.getJenisLowongan().getNama());
		return "view-detail-lowongan";
	}

}
