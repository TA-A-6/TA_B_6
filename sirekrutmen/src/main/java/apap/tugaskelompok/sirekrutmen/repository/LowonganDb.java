package apap.tugaskelompok.sirekrutmen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugaskelompok.sirekrutmen.model.LowonganModel;

@Repository
public interface LowonganDb extends JpaRepository<LowonganModel, Long>{
	Optional<LowonganModel> findById(Long id);
	

}
