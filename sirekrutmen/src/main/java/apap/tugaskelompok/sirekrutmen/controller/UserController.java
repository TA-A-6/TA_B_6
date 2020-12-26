package apap.tugaskelompok.sirekrutmen.controller;

import apap.tugaskelompok.sirekrutmen.rest.PegawaiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.GajiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.GajiDetails;
import apap.tugaskelompok.sirekrutmen.service.UserRestService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugaskelompok.sirekrutmen.model.RoleModel;
import apap.tugaskelompok.sirekrutmen.model.UserModel;
import apap.tugaskelompok.sirekrutmen.repository.RoleDb;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;
import apap.tugaskelompok.sirekrutmen.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDb roleDb;

	@Autowired
	private UserRestService userRestService;
	
	
	@RequestMapping("/add/{username}/{password}")
	public String addUserSubmit(
			@PathVariable("username") String username,
			@PathVariable("password") String password
	) {
		UserModel user = new UserModel();
		
		user.setPassword(password);
		user.setUsername(username);
		user.setRole(roleDb.findById(1L).get());
		user.setUuid(UUID.randomUUID().toString());
		userService.addUser(user);
		
		return "home";
		
	}

	@RequestMapping("/create")
	public String createUserForm(Model model) {
		List<RoleModel> listRole = roleDb.findAll();
		model.addAttribute("successMessage", "");
		model.addAttribute("listRole", listRole);
		return "create-user";
	}
	
	@PostMapping("/create")
	public String createUserSubmit(
			@RequestParam(value="nama", required=true) String nama,
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="roleId", required=true) Integer roleId,
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
		
		RoleModel role = roleDb.findById(Integer.toUnsignedLong(roleId)).get();
		newUser.setRole(role);
		
		userService.addUser(newUser);
		
		
		//crete pegawai to si-pegawai
		PegawaiDetail newPegawai = new PegawaiDetail();
		
		newPegawai.setNama(nama);
		newPegawai.setUsername(username);
		newPegawai.setAlamat(alamat);
		newPegawai.setTanggalLahir(tanggalLahir);
		newPegawai.setTempatLahir(tempatLahir);
		newPegawai.setRole(roleId);
		newPegawai.setNoTelepon(noTelepon);
		
		userRestService.postUserToSipegawai(newPegawai);
		
		//back to form
		List<RoleModel> listRole = roleDb.findAll();
		model.addAttribute("successMessage", "Pengguna berhasil ditambahkan");
		model.addAttribute("listRole", listRole);
		return "create-user";
		
	}
	

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(Model model){
		UserModel user = userService.getUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName());
		String username = user.getUsername();
		Boolean choose = false;
		try{
			PegawaiBaseResponse<PegawaiDetail> pegawai = userRestService.getDataPegawai(user.getUsername());
			choose = true;
			model.addAttribute("pegawai", pegawai.getResult());
//			model.addAttribute("role", pegawai.getResult().getRoleName());
		}catch(Exception e){

			choose = false;
		}
		model.addAttribute("role", user.getRole().getNama());
		model.addAttribute("choose", choose);
		model.addAttribute("username",username);
		return "profile";
	}

	@RequestMapping(value = "/salary_statistics", method=RequestMethod.GET)
	public String viewStatistics(Model model){
		GajiBaseResponse[] listGajiObject = userRestService.getStatistikGaji();

		List<GajiDetails> listGaji = new ArrayList<GajiDetails>();
		List<String> listPegawai= new ArrayList<String>();
		List<Long> lamaKerja = new ArrayList<Long>();
		int totalGaji = 0;

		for(int i=0; i<listGajiObject.length; i++){
			GajiBaseResponse gajiObject = listGajiObject[i];
			GajiDetails gaji = gajiObject.getGaji();
			totalGaji = totalGaji + gaji.getGajiPokok();
			Date masuk = gaji.getTanggalMasuk();
			ZoneId zoneId =ZoneId.systemDefault();
			Instant instant = masuk.toInstant();
			LocalDate tanggalMasuk = instant.atZone(zoneId).toLocalDate();
			Period period = Period.between(tanggalMasuk,LocalDate.now());
			Long years = Long.valueOf(period.getYears());
			listGaji.add(gaji);
			listPegawai.add(gajiObject.getUsername());
			lamaKerja.add(years);
		}
		model.addAttribute("pegawai",listPegawai);
		model.addAttribute("gaji",listGaji);
		model.addAttribute("lamaKerja", lamaKerja);
		model.addAttribute("rata",totalGaji/listGajiObject.length);
		return "salary";
	}

}
