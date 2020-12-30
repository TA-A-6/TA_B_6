package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.PelamarModel;
import apap.tugaskelompok.sirekrutmen.model.UserModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;
import apap.tugaskelompok.sirekrutmen.repository.UserDb;
import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.service.LamaranRestService;
import apap.tugaskelompok.sirekrutmen.service.LowonganService;
import apap.tugaskelompok.sirekrutmen.service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugaskelompok.sirekrutmen.service.LamaranService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Controller
public class LamaranController {
	@Autowired
	LamaranService lamaranService;

	@Autowired
	LamaranRestService lamaranRestService;

	@Autowired
	LowonganService lowonganService;

	@Autowired
	UserService userService;

	@Autowired
	UserDb userDb;

	@Autowired
	LamaranDb lamaranDb;

	@GetMapping("/lamaran/ubah-status/{idLamaran}")
	public String changeLamaranFormPage(
			@PathVariable Long idLamaran,
			Model model
	){
		LamaranModel lamaran = lamaranService.getLamaranById(idLamaran);
		int statusLamaran = 0;

		if (lamaran.getStatus() == 0){
			statusLamaran = 0;
		}
		else if(lamaran.getStatus() == 1){
			statusLamaran = 1;
		}
		else if(lamaran.getStatus() == 2){
			statusLamaran = 2;
		    Date dateTerima = new Date();
		    lamaran.setTanggalDiterima(dateTerima);
        }else{
			statusLamaran = 3;
		}

		model.addAttribute("statusLamaran", statusLamaran);
		model.addAttribute("lamaran", lamaran);
		return "update-status-lamaran";
	}


	@PostMapping("/lamaran/ubah")
	public String changeStatusLamaranFormSubmit(
			@ModelAttribute LamaranModel lamaran,
			Model model,
			RedirectAttributes redir
	){
		LamaranModel lamaranUpdated = lamaranService.ubahStatusLamaran(lamaran);

		LowonganModel lowonganLamaran = lamaranUpdated.getLowongan();
		int kapasitas = lowonganLamaran.getJumlah();
		int jumlahLamaran = lamaranService.getLamaranByLowonganAndStatus(lowonganLamaran,2).size();
		boolean cukup = false;

		if (jumlahLamaran == kapasitas){
			cukup = true;
			PelatihanDetail pelatihanDetail = new PelatihanDetail();
			pelatihanDetail.setNamaPelatihan("Pelatihan " + lowonganLamaran.getPosisi());
			pelatihanDetail.setDeskripsi("Pelatihan untuk para pelamar dari lowongan " + lowonganLamaran.getPosisi());
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

		if (cukup){
			redir.addFlashAttribute("msg", "Status pelamar dengan ID "+ lamaranUpdated.getIdLamaran() + " berhasil diubah dan pelatihan On Boarding!");
			redir.addFlashAttribute("type", "alert-success");
		}else {
			redir.addFlashAttribute("msg", "Status pelamar dengan ID "+ lamaranUpdated.getIdLamaran() + " berhasil diubah.");
			redir.addFlashAttribute("type", "alert-success");
		}

		return "redirect:/lowongan/detail/"+lamaranUpdated.getLowongan().getIdLowongan() ;
	}

    @GetMapping(value = "/lamaran/hapus/{idLamaran}")
    public String deleteLamaran(
            @PathVariable(value = "idLamaran") Long idLamaran,
            Model model,
			RedirectAttributes redir
	){

        LamaranModel lamaran = lamaranService.getLamaranById(idLamaran);

        if (lamaran.getStatus() == 3){
            model.addAttribute("lamaran", lamaran);
			redir.addFlashAttribute("msg", "Lamaran dengan ID "+ lamaran.getIdLamaran() + " berhasil dihapus.");
			redir.addFlashAttribute("type", "alert-success");
			lamaranService.deleteLamaran(lamaran);
        }else {
			redir.addFlashAttribute("msg", "Lamaran dengan ID "+ lamaran.getIdLamaran() + " tidak bisa dihapus karena status bukan ditolak.");
			redir.addFlashAttribute("type", "alert-danger");
        }
		return "redirect:/lowongan/detail/"+lamaran.getLowongan().getIdLowongan();
    }

	@GetMapping("/pelamar/tambah/{idLowongan}")
	public String tambahLamaran(@PathVariable Long idLowongan,
								RedirectAttributes redir
	) {

		UserModel username=userService.getUserByUsername(userService.getUserUsername());
		LowonganModel lowongan=lowonganService.getLowonganById(idLowongan);

		if(username.getRole().getIdRole()==7){
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());

			LamaranModel lamaran=new LamaranModel();
			//ASUMSI setStatus default 0 dan Tanggal Diterima default waktu skrng
			lamaran.setStatus(0);
			lamaran.setTanggalDiterima(date);
			lamaran.setPelamar(username.getPelamar());
//			lamaran.setIdLamaran(username.getPelamar().getIdPelamar());
			lamaran.setLowongan(lowongan);

			lamaranDb.save(lamaran);

			redir.addFlashAttribute("msg", "Lamaran Anda Berhasil ditambahkan!");
			redir.addFlashAttribute("type", "alert-success");
		}
		else{
			redir.addFlashAttribute("msg", "Lamaran tidak bisa ditambahkan, hanya user dengan role pelamar yang bisa menambahkan lamaran");
			redir.addFlashAttribute("type", "alert-danger");
		}
		return "redirect:/lowongan/detail/"+idLowongan ;
	}

	
}
