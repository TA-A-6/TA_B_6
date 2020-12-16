package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaskelompok.sirekrutmen.service.PelamarService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PelamarController {

	@Autowired
	PelamarService pelamarService;

	@Autowired
	LamaranService lamaranService;

	@RequestMapping("/pelamar")
	public String listLowongan(Model model) {
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
