package apap.tugaskelompok.sirekrutmen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugaskelompok.sirekrutmen.model.PelamarModel;

@Repository
public interface PelamarDb extends JpaRepository<PelamarModel, Long> {
	Optional<PelamarModel> findById(Long id);
	
}
