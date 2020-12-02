package apap.tugaskelompok.sirekrutmen.controller;

import org.springframework.stereotype.Controller;

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
import apap.tugaskelompok.sirekrutmen.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDb roleDb;
	
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
	
	

}
