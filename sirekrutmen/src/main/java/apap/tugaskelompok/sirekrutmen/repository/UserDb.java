package apap.tugaskelompok.sirekrutmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugaskelompok.sirekrutmen.model.UserModel;



@Repository
public interface UserDb extends JpaRepository<UserModel, String>{
	
	UserModel findByUsername(String username);


}
