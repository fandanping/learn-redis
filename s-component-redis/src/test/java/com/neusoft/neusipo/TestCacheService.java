package com.neusoft.neusipo;

import java.util.List;


/**
 * 
 * <p>[描述信息：说明类的基本功能]</p>
 *
 * @author 范丹平
 * @mail fandp@neusoft.com
 * @version 1.0 Created on 2017-9-4 下午02:24:46
 */
public interface TestCacheService {

	
	public void setCache(String value, UserTest textHTML);

	public String getCache(String value);
    
	
	public String getKey(String value);

	public void setCache(String... values);
	
	public void setExpire(String key, int sectonds);
	
	
}
