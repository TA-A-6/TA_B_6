package apap.tugaskelompok.sirekrutmen.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;



@Repository
public interface LamaranDb extends JpaRepository<LamaranModel, Long>{
	Optional<LamaranModel> findById(Long id);
	
	
}
