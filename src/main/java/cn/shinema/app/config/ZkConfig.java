package cn.shinema.app.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.shinema.core.port.adapter.lock.CuratorLock;

@Configuration
public class ZkConfig {

	@Value("${zookeeper.address}")
	private String zkAddress;

	@Value("${zookeeper.lockpath:zklock}")
	private String zkLockPath;

	@Bean
	public CuratorFramework curatorFramework() {
		CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
		builder.connectString(zkAddress);
		builder.sessionTimeoutMs(5000);
		builder.connectionTimeoutMs(3000);

		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		builder.retryPolicy(retryPolicy);

		CuratorFramework newClient = builder.build();
		newClient.start();
		return newClient;
	}

	@Bean
	public CuratorLock CuratorLock(CuratorFramework curatorFramework) {
		return new CuratorLock(curatorFramework, zkLockPath);
	}
}
