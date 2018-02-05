package com.neusoft.neusipo;

import java.util.List;

import com.neusoft.neusipo.components.redis.commons.AbstractRedisCache;


public class TestCacheServiceImpl extends AbstractRedisCache implements TestCacheService {
  
	/**
    * 获取缓存中的值
    * 调用方法：getString(getKey(value)) 依据Key值获取相应的值
    * @see com.neusoft.neusipo.TestCacheService.menu.bizservice.MenuCacheService#getCache(java.lang.String)
    */
	@Override
	public String getCache(String value){
		return getString(getKey(value));
	}
   

    /**
     * 自定义缓存数据key值生成策略
     * @see com.neusoft.neusipo.TestCacheService.menu.bizservice.MenuCacheService#getKey(com.neusoft.neusipo.apis.menu.domain.Menu)
     */
	@Override
	public String getKey(String menu){
		// TODO Auto-generated method stub
		return menu;
	}
     
	/**
	 * 把值放入缓存中
	 * @see com.neusoft.neusipo.TestCacheService.menu.bizservice.MenuCacheService#setCache(java.lang.String, java.lang.String)
	 */
	@Override
	public void setCache(String value, UserTest textHTML) {
		///setString(getKey(value),textHTML);	
	}
     
	@Override
	public void setCache(String... values) {
		setStrings(values);		
	}
	
	public void setExpire(String key, int seconds){
		super.setExpire(key, seconds);
	}
  
}
