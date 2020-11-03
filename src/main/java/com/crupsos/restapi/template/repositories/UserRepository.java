package com.crupsos.restapi.template.repositories;

import com.crupsos.restapi.template.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByEmail(String email);
}
