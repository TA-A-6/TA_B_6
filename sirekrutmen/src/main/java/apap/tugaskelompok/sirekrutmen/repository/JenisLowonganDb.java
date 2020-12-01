package apap.tugaskelompok.sirekrutmen.repository;


import apap.tugaskelompok.sirekrutmen.model.JenisLowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JenisLowonganDb extends JpaRepository<JenisLowonganModel, Long> {
    Optional<JenisLowonganModel> findById(Long id);
}