package com.stqin.stydy.java.redis;

import redis.clients.jedis.*;

import java.io.Serializable;

/**
 * JedisPool工具类，设置一个全局连接池，供系统获取redis客户端
 *
 * @date 2018年5月8日
 */
public class RedisPoolUtils implements Serializable {
    private static int connectionTimeout = 200000;
    private static String redisIp;
    private static int redisPort;
    private static int redisSlot = 0;
    private static JedisPool jedisPool = null;

    // 初始化连接池
    static {
        synchronized (RedisPoolUtils.class) {
            if (jedisPool == null) {
                String redisIpAndPortStr = "redisIpAndPort";
                String redisSlotStr = "redisSlot";
                String redisAuth = "redisPassword";
                if (redisIpAndPortStr != null && redisSlotStr != null) {
                    String[] redisIpAndPort = redisIpAndPortStr.split(":");
                    redisIp = redisIpAndPort[0];
                    redisPort = Integer.valueOf(redisIpAndPort[1]);
                    redisSlot = Integer.parseInt(redisSlotStr);
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxWaitMillis(100 * 1000);// 当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
                    config.setMaxIdle(100);// 资源池允许最大空闲的连接数
                    config.setMaxTotal(200);// 资源池中最大连接数
                    config.setTestOnBorrow(true);// 向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除,业务量很大时候建议设置为false(多一次ping的开销)
                    jedisPool = new JedisPool(config, redisIp, redisPort, connectionTimeout, redisAuth);
                }
            }
        }
    }

    /**
     * 可以显示调用设置RedisPool的方法
     *
     * @param ipAndPort ip和端口
     * @param redisSlotStr 槽号
     * @param redisAuth 密码，若没有则传null
     */
    public static synchronized void setIpAndPort(String ipAndPort, String redisSlotStr, String redisAuth) {
        String[] redisIpAndPort = ipAndPort.split(":");
        redisIp = redisIpAndPort[0];
        redisPort = Integer.valueOf(redisIpAndPort[1]);
        redisSlot = Integer.parseInt(redisSlotStr);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(100 * 1000);// 当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
        config.setMaxIdle(100);// 资源池允许最大空闲的连接数
        config.setMaxTotal(200);// 资源池中最大连接数
        config.setTestOnBorrow(true);// 向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除,业务量很大时候建议设置为false(多一次ping的开销)
        jedisPool = new JedisPool(config, redisIp, redisPort, connectionTimeout, redisAuth);
    }

    /**
     * 从连接池中获取客户端
     *
     * @return
     */
    public static Jedis getClient() {
        Jedis redis = jedisPool.getResource();
        redis.select(redisSlot);
        return redis;
    }

    /**
     * 获取指定槽号的客户端
     *
     * @param index
     * @return
     */
    public static Jedis getClient(int index) {
        Jedis redis = jedisPool.getResource();
        redis.select(index);
        return redis;
    }

    public static int getRedisSlot() {
        return redisSlot;
    }
}
