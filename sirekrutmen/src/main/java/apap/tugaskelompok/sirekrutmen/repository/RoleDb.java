
package apap.tugaskelompok.sirekrutmen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugaskelompok.sirekrutmen.model.RoleModel;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
	Optional<RoleModel> findById(Long id);
	
}
