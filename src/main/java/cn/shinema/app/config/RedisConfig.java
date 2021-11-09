package cn.shinema.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import cn.shinema.core.config.RedisCommonConfig;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class RedisConfig extends RedisCommonConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.database}")
	private int database;

	@Value("${redis.cacheName:}")
	private String cacheName;

	@Value("${redis.ttl:60}")
	private int ttl;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return super.jedisConnectionFactory(host, port, password, database);
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		return super.cacheManager(redisConnectionFactory, ttl, cacheName);
	}

	@Bean
	RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		return super.getRedisTemplate(redisConnectionFactory);
	}

//    @Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
