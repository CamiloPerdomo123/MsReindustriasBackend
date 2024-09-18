package com.msreindustrias.securityjwt.infrastructure.repository;

import com.msreindustrias.securityjwt.domain.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
}
