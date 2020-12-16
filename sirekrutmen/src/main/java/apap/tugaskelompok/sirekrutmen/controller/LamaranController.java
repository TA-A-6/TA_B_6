package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.service.LamaranRestService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugaskelompok.sirekrutmen.service.LamaranService;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
public class LamaranController {
	@Autowired
	LamaranService lamaranService;

	@Autowired
	LamaranRestService lamaranRestService;

	@GetMapping("/lamaran/ubah-status/{idLamaran}")
	public String changeLamaranFormPage(
			@PathVariable Long idLamaran,
			Model model
	){
		LamaranModel lamaran = lamaranService.getLamaranById(idLamaran);

		if(lamaran.getStatus() == 2){
		    Date dateTerima = new Date();
		    lamaran.setTanggalDiterima(dateTerima);
        }
		model.addAttribute("lamaran", lamaran);
		return "update-status-lamaran";
	}


	@PostMapping("/lamaran/ubah")
	public String changeStatusLamaranFormSubmit(
			@ModelAttribute LamaranModel lamaran,
			Model model
	){
		LamaranModel lamaranUpdated = lamaranService.ubahStatusLamaran(lamaran);

		LowonganModel lowonganLamaran = lamaranUpdated.getLowongan();
		int kapasitas = lowonganLamaran.getJumlah();
		int jumlahLamaran = lamaranService.getLamaranByLowonganAndStatus(lowonganLamaran,2).size();
		boolean cukup = false;

		if (jumlahLamaran == kapasitas){
			cukup = true;
			PelatihanDetail pelatihanDetail = new PelatihanDetail();
			pelatihanDetail.setNamaPelatihan("Pelatihan" + lowonganLamaran.getPosisi());
			pelatihanDetail.setDeskripsi("Pelatihan untuk para pelamar dari lowongan" + lowonganLamaran.getPosisi());
			pelatihanDetail.setKapasitas(kapasitas);

			java.util.Date mulai = new java.util.Date();
			Calendar ca = Calendar.getInstance();
			ca.setTime(mulai);
			ca.add(Calendar.DATE, 7);
			Date tanggalMulai = ca.getTime();
			pelatihanDetail.setTanggalMulai(tanggalMulai);

			java.util.Date selesai = new java.util.Date();
			Calendar c = Calendar.getInstance();
			c.setTime(selesai);
			c.add(Calendar.DATE, 5);
			Date tanggalSelesai = c.getTime();
			pelatihanDetail.setTanggalSelesai(tanggalSelesai);

			java.util.Date dateWaktuMulai = new java.util.Date();
			pelatihanDetail.setWaktuMulai(dateWaktuMulai);
			java.util.Date dateWaktuSelesai = new java.util.Date();
			pelatihanDetail.setWaktuSelesai(dateWaktuSelesai);
			lamaranRestService.postLamaran(pelatihanDetail);
		}
		model.addAttribute("cukup", cukup);
		model.addAttribute("lamaran", lamaranUpdated);
		System.out.println(cukup);
		return "update-lamaran";
	}

	
}
