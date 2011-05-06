package com.xinlv.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xinlv.repository.UserRepository;
import com.xinlv.test.MockContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = MockContextLoader.class,
        locations = {"/xinlv-test-context.xml" })
@Transactional        
public class DomainTest {
	
	@Autowired UserRepository userRepo;

	@Test 
	public void testUser() {
		User jon = new User("Jon").persist();
		User emil = new User("Emil").persist();
		User rod = new User("Rod").persist();
		
		emil.knows(jon);
		ConcernTo emilConcernRod = emil.concern(rod,new Date());
		
		emil.persist();
		
		User retrivedEmil = userRepo.findByPropertyValue("login", "Emil");
		assertEquals("retrivedEmil name is equal","Emil",retrivedEmil.getName());
		assertEquals("there is 3 users",new Long(userRepo.count()),new Long(3L));
		
		ConcernTo firstConcern = retrivedEmil.getConcerns().iterator().next();
		
		assertEquals("emil concern to rod",rod,firstConcern.getConcerned());
		
		User friend = retrivedEmil.getFriends().iterator().next();
		
		assertEquals("jon is emil's friend",friend,jon);
		
	}
}
