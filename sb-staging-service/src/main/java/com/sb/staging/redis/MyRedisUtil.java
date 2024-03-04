package com.sb.staging.redis;

import com.sb.staging.json.RedisJsonUtil;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: MMDZ
 * @Date: 2021/10/18
 * @Desc: TODO 自定义redis工具类（单独放一些自己业务需要的方法）
 */
@Component
public class MyRedisUtil extends RedisUtil {

    public MyRedisUtil(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    /**
     * @param key
     * @param value     为对象时flag_json必须为true
     * @param db        缓存的数据库
     * @param flag_json 是否将value值转为json
     * @param timeOut   时效（秒）   永久传null
     * @return boolean
     * @Title: set
     * @Description: 写入缓存并指定库
     */
    public boolean set(final String key, Object value, int db, boolean flag_json, Long timeOut) {
        boolean result = false;
        try {
            RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
            DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            stringRedisConnection.select(db);
            if (flag_json) {
                stringRedisConnection.set(key, RedisJsonUtil.obj2String(value));
            } else {
                stringRedisConnection.set(key, value.toString());
            }
            if (timeOut != null && timeOut != 0) {
                stringRedisConnection.expire(key, timeOut);
            }
            stringRedisConnection.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param db
     * @return Object
     * @Title: get
     * @Description: 读取指定db的缓存
     */
    public Object get(final String key, int db) {
        Object result = null;
        try {
            RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
            DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            stringRedisConnection.select(db);
            result = stringRedisConnection.get(key);
            stringRedisConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除指定db的key
     *
     * @param key
     * @param db
     */
    public void remove(final String key, int db) {
        try {
            RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
            DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
            stringRedisConnection.select(db);
            if (stringRedisConnection.exists(key)) {
                stringRedisConnection.del(key);
            }
            stringRedisConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}