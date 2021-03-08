package br.com.italosilva.vehicleapi.repository;

import br.com.italosilva.vehicleapi.model.VehicleJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleJpaEntity, Long> {

    Optional<VehicleJpaEntity> findByPlate(String plate);

    Page<VehicleJpaEntity> findByPlate(String plate, Pageable pageable);

    Page<VehicleJpaEntity> findByStatusTrue(Pageable pageable);
}
