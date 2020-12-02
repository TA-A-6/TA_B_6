package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.UserModel;

import java.util.List;

public interface UserService {

	UserModel getUserByUsername(String username);
	List<UserModel> findAll();
	
	UserModel addUser(UserModel user);
	String encrypt(String password);

}
