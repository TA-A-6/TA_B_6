package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaskelompok.sirekrutmen.model.UserModel;
import apap.tugaskelompok.sirekrutmen.repository.UserDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserDb userDb;
	/*
	  Your code goes here.
	  Jangan lupa isi dulu interface nya sebelum ngoding disini
	  
	  -Rian
	 */

	@Override
	public UserModel getUserByUsername(String username) {
		
		return userDb.findByUsername(username);
	}

	@Override
	public List<UserModel> findAll(){return userDb.findAll();}

}
