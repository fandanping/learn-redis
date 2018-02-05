package com.neusoft.neusipo.components.redis.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * <p>[描述信息：redis的可扩展工具类]</p>
 *   
 * @author 范丹平
 * @mail fandp@neusoft.com
 * @version 1.0 Created on 2017-9-4 上午10:53:04
 * @param <T>
 */
public abstract class AbstractRedisCache<T> {
	 /**  
     * 成功,1L  
     */    
    private static final Long SUCCESS_STATUS_LONG = 1L;    
   
	private JedisCluster jedisCluster;

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	/**
	 * 
	 * <p>[设置缓存，指定秒数后过期]</p>
	 * 
	 * @param key
	 * @param value
	 * @param second
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-4 上午11:13:37
	 */
	public  boolean setString(String key,String value ,int second){
		jedisCluster.setex(key, second, value);
		return true;
	}
	
	/**
	 * 
	 * <p>[设置缓存key-value]</p>
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-4 上午11:19:55
	 */
	public boolean setString(String key,String value){
		jedisCluster.set(key,value);
		return true;
	}
	/**
	 * 
	 * <p>缓存List数据</p>
	 * 提示：方法gson.toJson()是将指定类型的数据转成字符串
	 * @param key
	 * @param dataList
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-5 下午04:19:49
	 */
	public boolean setList(String key,List<T> dataList){
		if(null != dataList){
			Gson gson = new Gson();
			jedisCluster.set(key, gson.toJson(dataList));
		 }
		return true;
	}
	
	
	/**
	 * 
	 * <p>[缓存Set数据]</p>
	 * 
	 * @param key
	 * @param dataList
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午09:31:36
	 */
	public boolean setCacheSet(String key,Set<T> dataList){
		if(null != dataList){
			Gson gson=new Gson();
			jedisCluster.set(key,gson.toJson(dataList));
		}
		return true;
	}
	/**
	 * 
	 * <p>[缓存Map数据集]</p>
	 * 
	 * @param key
	 * @param dataList
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午09:52:29
	 */
	
	public boolean  setCacheMap(String key,Map<String,T> dataList){
		if(dataList !=null){
			Gson gson=new Gson();
			jedisCluster.set(key,gson.toJson(dataList));
		}
		return true;
	}
	
	
	/**
	 * 
	 * <p>[设置多个key,value]</p>
	 * 
	 * @param keys
	 * @return: void
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-4 上午11:35:39
	 */
	public void setStrings(String...keys){
		if(keys.length % 2 !=0){
			throw new IllegalArgumentException();
		}
		for(int i=0,l=keys.length;i<l;i=i+2){
			jedisCluster.set(keys[i],keys[i+1]);
		}
	}
	
	/**
	 * 
	 * <p>[依据指定的Key获取缓存中指定的value]</p>
	 * 
	 * 注意：放进缓存的全是String，所以取得也全是String，可以从缓存中拿到String串后用gson做转换
	 * 
	 * @param key
	 * @return
	 * @return: String
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-4 上午11:21:49
	 */
	public String getString(String key){
		return jedisCluster.get(key);
	}
	
    /**
     * 
     * <p>[批量获取多个key对应得values值]</p>
     * 
     * @param keys
     * @return
     * @return: List<String>
     * @author: 范丹平
     * @mail: fandp@neusoft.com
     * @date: Created on 2017-9-4 上午11:43:46
     */
	public List<String> getStrings(String...keys){
		List<String> list=new ArrayList<String>();
		for(String key :keys){
			if(jedisCluster.exists(key)){
				list.add(jedisCluster.get(key));
				
			} else{
				list.add(null);
			}
		}
		return list;
	}
	/**
	 * 
	 * <p>[设置超时时间]</p>
	 * 
	 * @param key
	 * @param seconds
	 * @return: void
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午10:00:12
	 */
	public void setExpire(String key, int seconds){
		jedisCluster.expire(key, seconds);
	}
    
	/**
	 * 
	 * <p>[判断key是否存在]</p>
	 * 
	 * @param key
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午10:02:44
	 */
	public boolean isExists(String key){
		return  jedisCluster.exists(key);
	}
	
	/**
	 * 
	 * <p>[判断key是否过期]</p>
	 * 
	 * @param key
	 * @param item
	 * @return
	 * @return: Long
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-7 下午03:24:09
	 */
	public Long hdel(String key,String item){
		return jedisCluster.hdel(key, item);
	}
	
	
	
	/**
	 * 
	 * <p>[删除key及值]</p>
	 * 
	 * @param key
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午10:10:59
	 */
	public  boolean removeKey(String key){
		if(SUCCESS_STATUS_LONG == jedisCluster.del(key)){
			return true;
		}else{
			return false;
		}		
	}
	
	/**
	 * 
	 * <p>[批量删除key值]</p>
	 * 
	 * @param keys
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 上午10:35:29
	 */
	public boolean  batchRemoveKeys(String[] keys){
		int statusCode=0;;
		for(String key :keys){
			jedisCluster.del(key);
			statusCode++;
		}
		if(statusCode ==keys.length){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * <p>[获取剩余时间：秒]</p>
	 * 
	 * @param key
	 * @return
	 * @return: long
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-6 下午02:23:52
	 */
	public long getTime(String key){
		return jedisCluster.ttl(key);
	}
	
	/**
	 * 
	 * <p>[设置hash数据类型]</p>
	 * 
	 * @param key
	 * @param item
	 * @param value
	 * @return
	 * @return: boolean
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-7 下午03:17:26
	 */
	public boolean hset(String key,String item,String value){
		jedisCluster.hset(key, item, value);
		return true;
	}
	
	
   /**
    * 
    * <p>[获取hash数据类型]</p>
    * 
    * @param key
    * @param item
    * @return
    * @return: String
    * @author: 范丹平
    * @mail: fandp@neusoft.com
    * @date: Created on 2017-9-7 下午03:19:33
    */
	public String hget(String key,String item ){
		return jedisCluster.hget(key, item);
	}
	
	/**
	 * 
	 * <p>[删除hash数据]</p>
	 * 
	 * @param key
	 * @return
	 * @return: Long
	 * @author: 范丹平
	 * @mail: fandp@neusoft.com
	 * @date: Created on 2017-9-7 下午03:20:56
	 */
	public Long incr(String key){
		return jedisCluster.incr(key);
		
	}
	
   
	
	
	
	
	
	
	
	
	
	
	
	
}
