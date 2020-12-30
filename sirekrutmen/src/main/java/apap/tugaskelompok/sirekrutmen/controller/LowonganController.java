package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.*;
import apap.tugaskelompok.sirekrutmen.repository.LowonganDb;
import apap.tugaskelompok.sirekrutmen.service.JenisLowonganService;
import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import apap.tugaskelompok.sirekrutmen.service.UserService;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LowonganController {
	@Autowired
	LowonganService lowonganService;

	@Autowired
	LamaranService lamaranService;

	@Autowired
	UserService userService;

	@Autowired
	JenisLowonganService jenisLowonganService;

	@Autowired
	LowonganDb lowonganDb;
	

	@RequestMapping("/lowongan")
	public String listLowongan(Model model,
							   Authentication auth){

		UserModel user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<LowonganModel> listLowongan = lowonganService.getListLowongan();

		String role = userService.getUserByUsername(auth.getName()).getRole().getNama();
		model.addAttribute("role",role);

		List<LowonganModel> listLowonganPJ = new ArrayList<LowonganModel>();
		for (int i = 0; i < listLowongan.size(); i++) {
			if(listLowongan.get(i).getUser().equals(user)){
				listLowonganPJ.add(listLowongan.get(i));
			}
		}

		model.addAttribute("listLowonganPJ", listLowonganPJ);
		model.addAttribute("listLowongan", listLowongan);
		return "viewall-lowongan";
	}


	@GetMapping("/lowongan/add")
	public String addLowonganFormPage(Model model){
		model.addAttribute("lowongan", new LowonganModel());
		List<JenisLowonganModel> listJenis = jenisLowonganService.findAll();
		model.addAttribute("listJenis", listJenis);
		return "form-add-lowongan";
	}

	@PostMapping("/lowongan/add")
	public String addLowonganSubmit(
			@ModelAttribute LowonganModel lowongan,
			RedirectAttributes redir){
		String code = lowonganService.getKode(lowongan);
		lowongan.setKodeLowongan(code);

		lowongan.setUser(userService.getUserByUsername(userService.getUserUsername()));

		lowonganDb.save(lowongan);
		redir.addFlashAttribute("msg", "Lowongan dengan kode "+ lowongan.getKodeLowongan() + " berhasil ditambahkan.");
		redir.addFlashAttribute("type", "alert-success");

		return "redirect:/lowongan";
	}


	@GetMapping("/lowongan/ubah/{idLowongan}")
	public String changeLowonganFormPage(
			@PathVariable Long idLowongan,
			Model model
	){

		LowonganModel lowongan = lowonganService.getLowonganById(idLowongan);
		if (lowongan == null){
			return "lowongan-error";
		} else {
			model.addAttribute("lowongan", lowongan);
			List<UserModel> listUser = userService.findAll();
			List<JenisLowonganModel> listJenis = jenisLowonganService.findAll();
			model.addAttribute("size", lowongan.getListLamaran().size());
			model.addAttribute("listJenis", listJenis);
			model.addAttribute("listUser", listUser);
			return "update-lowongan-form";
		}
	}

	@PostMapping("/lowongan/ubah")
	public String changeLowonganFormSubmit(

			@ModelAttribute LowonganModel lowongan,
			Model model, RedirectAttributes redir
	){
		LowonganModel lowonganUpdated = new LowonganModel();
		if (lowongan.getDivisi()== null || lowongan.getPosisi() == null || lowongan.getJenisLowongan() == null){
			lowonganUpdated = lowonganService.updateLowonganVer2(lowongan);
			model.addAttribute("lowonganUpdated", lowonganUpdated);
		} else {
			lowonganUpdated = lowonganService.updateLowongan(lowongan);
			String newCode = lowonganService.getKode(lowonganUpdated);
			lowonganUpdated.setKodeLowongan(newCode);
			lowonganDb.save(lowonganUpdated);
			model.addAttribute("lowonganUpdated", lowonganUpdated);
		}

		redir.addFlashAttribute("msg", "Lowongan dengan kode "+ lowonganUpdated.getKodeLowongan()  + " berhasil diupdate.");
		redir.addFlashAttribute("type", "alert-success");
		return "redirect:/lowongan/detail/"+lowonganUpdated.getIdLowongan();

	}

	
	@GetMapping("/lowongan/detail/{id}")
	public String detailLowongan(
			@PathVariable(value="id") Long id,
			Model model,
			Authentication auth
	){
		String role = userService.getUserByUsername(auth.getName()).getRole().getNama();
		model.addAttribute("role",role);
		LowonganModel lowongan = lowonganService.getLowonganById(id);
		List<PelamarModel> daftarPelamar = new ArrayList<>();
		List<LamaranModel> daftarLamaran = lamaranService.getAllLamaranByIdLowongan(id);

		for (LamaranModel lamaran : daftarLamaran) {
			daftarPelamar.add(lamaran.getPelamar());
		}

		model.addAttribute("lowongan",lowongan);
		model.addAttribute("pelamar",daftarPelamar);
		model.addAttribute("daftarLamaran", daftarLamaran);
		model.addAttribute("jenisLowongan",lowongan.getJenisLowongan().getNama());
		return "view-detail-lowongan";
	}

	@RequestMapping("/lowongan/hapus/{id}")
	public String deleteLowongan(
			@PathVariable(value="id") Long id,
			RedirectAttributes redir
	){
		LowonganModel lowongan = lowonganService.getLowonganById(id);
		List<LamaranModel> daftarLamaran = lowongan.getListLamaran();
		Boolean statusLamaran = true;
		for (int i = 0; i < daftarLamaran.size(); i++){
			if(daftarLamaran.get(i).getStatus()==1 || daftarLamaran.get(i).getStatus()==0){
				statusLamaran = false;
			}
		}

		if(statusLamaran){
			lowonganService.deteleLowongan(lowongan);
			redir.addFlashAttribute("msg", "Lowongan dengan kode "+ lowongan.getKodeLowongan() + " berhasil dihapus.");
			redir.addFlashAttribute("type", "alert-success");
		}else{
			redir.addFlashAttribute("msg", "Lowongan dengan kode "+ lowongan.getKodeLowongan()+" masih memiliki pelamar dengan status melamar atau wawancara");
			redir.addFlashAttribute("type", "alert-danger");
		}
		return "redirect:/lowongan";

	}


}
