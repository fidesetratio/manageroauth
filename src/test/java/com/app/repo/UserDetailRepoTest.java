package com.app.repo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.OauthUsers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepoTest {
	
	@Autowired
	private	UserDetailRepo userRepo;
	
	
	@Test
	public void testFindAllRepo() {
		List<OauthUsers> oauthUsers = (List<OauthUsers>) userRepo.findAll();
		System.out.println("size="+oauthUsers.size());
		Assert.assertTrue(oauthUsers.size()>0);
		
	}
	
}
