package com.crupsos.restapi.template.registry;

import com.crupsos.restapi.template.models.RoleModel;
import com.crupsos.restapi.template.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RoleRegistry {

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {

            RoleModel adminRoleRegistry = roleRepository.findByRole("ROLE_ADMIN");
            if(adminRoleRegistry == null) {
                RoleModel adminRole = new RoleModel();
                adminRole.setRole("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            RoleModel userRoleRegistry = roleRepository.findByRole("ROLE_USER");
            if(userRoleRegistry == null) {
                RoleModel userRole = new RoleModel();
                userRole.setRole("ROLE_USER");
                roleRepository.save(userRole);
            }

        };
    }
}
