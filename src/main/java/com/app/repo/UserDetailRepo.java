package com.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.model.ClientDetails;
import com.app.model.OauthUsers;

public interface UserDetailRepo extends CrudRepository<OauthUsers,Long> {
	
}
