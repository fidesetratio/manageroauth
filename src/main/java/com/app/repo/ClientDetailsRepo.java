package com.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.model.ClientDetails;

public interface ClientDetailsRepo extends CrudRepository<ClientDetails,String>{

}
