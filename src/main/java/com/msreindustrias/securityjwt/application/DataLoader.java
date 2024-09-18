package com.msreindustrias.securityjwt.application;

import com.msreindustrias.securityjwt.domain.entity.RolEntity;
import com.msreindustrias.securityjwt.infrastructure.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {

        // Verificar si ya existen roles
        if (rolRepository.count() == 0) {

            // Insertar roles solo si no existen
            RolEntity admin = new RolEntity(1, "ADMIN");
            RolEntity empleado = new RolEntity(2, "EMPLEADO");
            RolEntity cliente = new RolEntity(3, "CLIENTE");

            rolRepository.save(admin);
            rolRepository.save(empleado);
            rolRepository.save(cliente);

            System.out.println("Roles predeterminados insertados");
        }
    }
}
