package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Override
	public UserModel getUserByUsername(String username) {
		
		return userDb.findByUsername(username);
	}

	@Override
	public List<UserModel> findAll(){return userDb.findAll();}

	@Override
	public UserModel addUser(UserModel user) {
		String password = encrypt(user.getPassword());
		user.setPassword(password);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public String getUserUsername(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)){
			String currentUserName = ((Authentication) authentication).getName();
			return currentUserName;
		}
		return null;
	}

	@Override
	public String getRoleByUsername(String username){
		return userDb.findAllByUsername(username);
	}

}
