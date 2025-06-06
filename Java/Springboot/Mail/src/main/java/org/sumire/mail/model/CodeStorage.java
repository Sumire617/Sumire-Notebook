package org.sumire.mail.model;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class CodeStorage {
    private static final JedisPool jedisPool;
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    /**
     * 保存验证码
     * @param email 邮箱
     * @param code 验证码
     */
    public static void saveCode(String email, String code) {
        try (Jedis jedis = jedisPool.getResource()) {
            // 设置验证码并添加5分钟过期时间
            jedis.setex(email, 300, code);
        }
    }

    public static boolean verifyCode(String email, String inputCode) {
        try (Jedis jedis = jedisPool.getResource()) {
            String savedCode = jedis.get(email);
            return savedCode != null && savedCode.equals(inputCode);
        }
    }
}
