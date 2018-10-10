package com.duia.mars.configurer;

import com.duia.mars.configurer.redis.CommonRedis;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/11.
 */
@Configuration
@EnableCaching
public class RedisConfigurer extends CachingConfigurerSupport {

    @Autowired
    private CommonRedis commonRedis;

    @Value("${redis.en.db}")
    private int enIndex;

    @Value("${redis.point.db}")
    private int pointIndex;

    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate<?, ?> redisTemplate) {
        // 创建缓存管理器
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间（秒）
        cacheManager.setDefaultExpiration(3600 * 24);
        return cacheManager;
    }


    @Bean(name = "enRedisTemplate")
    public RedisTemplate msgRedisTemplate() {
        return redisDbTemplate(enIndex);
    }


    @Bean(name = "saleRedisTemplate")
    public RedisTemplate saleRedisTemplate() {
        return redisDbTemplate(pointIndex);
    }

    public RedisTemplate redisDbTemplate(Integer index) {
        StringRedisTemplate template = new StringRedisTemplate(connectionFactory(index));
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private RedisConnectionFactory connectionFactory(Integer index) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(commonRedis.getHost());
        jedis.setPort(commonRedis.getPort());
        if (!org.apache.commons.lang3.StringUtils.isEmpty(commonRedis.getPassword())) {
            jedis.setPassword(commonRedis.getPassword());
        }
        if (null == index) {
            jedis.setDatabase(commonRedis.getDatabase());
        } else {
            jedis.setDatabase(index);
        }
        jedis.setTimeout(commonRedis.getTimeout());
        jedis.setPoolConfig(poolCofig(commonRedis.getMaxIdle(), commonRedis.getMaxTotal(), commonRedis.getMaxWaitMillis(),
                commonRedis.isTestOnBorrow(), commonRedis.isTestOnReturn()));
        RedisConnectionFactory factory = jedis;
        jedis.afterPropertiesSet();
        return factory;
    }


    private JedisPoolConfig poolCofig(int maxIdle, int maxTotal,
                                      long maxWaitMillis, boolean testOnBorrow, boolean testOnReturn) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        poolCofig.setTestOnReturn(testOnReturn);
        return poolCofig;
    }


}
