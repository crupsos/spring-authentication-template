package com.crupsos.restapi.template.repositories;

import com.crupsos.restapi.template.models.RoleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<RoleModel, String> {

    RoleModel findByRole(String role);
}
