package com.neusoft.neusipo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.neusipo.components.redis.commons.AbstractRedisCache;



public class RedisTest {
	
	private AbstractRedisCache abstractRedisCache=null;

	public void setAbstractRedisCache(AbstractRedisCache abstractRedisCache) {
		this.abstractRedisCache = abstractRedisCache;
	}
    
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");  
		
	}
	
	
	    public void testAddUser() {  
	        UserTest user = new UserTest();  
	        user.setId("user1");  
	        user.setName("java2000_wl");  
	     //   abstractRedisCache.setString("111", user);
	        
	    }  
	
}
