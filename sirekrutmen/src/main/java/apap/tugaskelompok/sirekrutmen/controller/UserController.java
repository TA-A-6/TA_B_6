package apap.tugaskelompok.sirekrutmen.controller;

import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaskelompok.sirekrutmen.model.RoleModel;
import apap.tugaskelompok.sirekrutmen.model.UserModel;
import apap.tugaskelompok.sirekrutmen.repository.RoleDb;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;
import apap.tugaskelompok.sirekrutmen.service.UserRestService;
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
	
//	buat testing doang
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
	

}
