package com.msreindustrias.securityjwt.infrastructure.repository;

import com.msreindustrias.securityjwt.domain.entity.DatosPersonalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDatosPersonalesRepository extends JpaRepository<DatosPersonalesEntity, Long> {
    DatosPersonalesEntity findFirstByNumeroDocumento(String numeroDocumento);

}
