package com.gzf.sypt.cache;

import com.gzf.sypt.util.JavaSerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Jedis jedisClient = createRedis();

    private String id;

    public RedisCache(final String id){
        if (id == null){
            throw new IllegalArgumentException("cache instances cannot get an id");
        }
        this.id = id;
    }

    private static Jedis createRedis() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
        return pool.getResource();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        jedisClient.append(JavaSerializeUtil.serialize(key),JavaSerializeUtil.serialize(value));
    }

    @Override
    public Object getObject(Object key) {
        byte[] bytes = jedisClient.get(JavaSerializeUtil.serialize(key));
        if (bytes != null){
            return JavaSerializeUtil.unSerialize(bytes);
        }
        return null;
    }

    //其实就是把生存时间设置为0来达到删除的目的
    @Override
    public Object removeObject(Object key) {
        return jedisClient.expire(JavaSerializeUtil.serialize(key),0);
    }

    //刷新缓存内存
    @Override
    public void clear() {
        jedisClient.flushDB();
    }

    //获取缓存内存大小
    @Override
    public int getSize() {
        return Integer.valueOf(jedisClient.dbSize().toString());
    }

    //设置读写锁，允许多读一写
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
