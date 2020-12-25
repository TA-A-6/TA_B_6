package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.*;
import apap.tugaskelompok.sirekrutmen.repository.PelamarDb;
import apap.tugaskelompok.sirekrutmen.repository.RoleDb;
import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import apap.tugaskelompok.sirekrutmen.service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;

import java.util.List;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.service.PelamarService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PelamarController {

	@Autowired
	PelamarService pelamarService;

	@Autowired
	UserService userService;

	@Autowired
	RoleDb roleDb;

	@Autowired
	PelamarDb pelamarDb;

	@RequestMapping("/pelamar/create")
	public String createPelamarForm(Model model) {
		model.addAttribute("successMessage", "");
		return "create-pelamar";
	}

	@PostMapping("/pelamar/create")
	public String createPelamarSubmit(
			@RequestParam(value="nama", required=true) String nama,
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="noTelepon", required=true) String noTelepon,
			@RequestParam(value="tempatLahir", required=true) String tempatLahir,
			@RequestParam(value="tanggalLahir", required=true) Date tanggalLahir,
			@RequestParam(value="alamat", required=true) String alamat,
			Model model

	) {

		// create user in si-rekrutmen
		UserModel newUser = new UserModel();

		newUser.setUsername(username);
		newUser.setPassword(password);

		RoleModel role = roleDb.findById(Integer.toUnsignedLong(7)).get();
		newUser.setRole(role);

		newUser.setUuid(UUID.randomUUID().toString());

		userService.addUser(newUser);



		//crete pelamar
		PelamarModel newPelamar = new PelamarModel();
		newPelamar.setNama(nama);
		newPelamar.setAlamat(alamat);


		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		newPelamar.setTanggalLahir(tanggalLahir);

		newPelamar.setTempatLahir(tempatLahir);
		newPelamar.setNoTelepon(noTelepon);
		newPelamar.setUser(userService.getUserByUsername(username));

		pelamarDb.save(newPelamar);

		//back to form

		model.addAttribute("successMessage", "Calon pelamar berhasil ditambahkan");

		return "create-pelamar";

	}


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
