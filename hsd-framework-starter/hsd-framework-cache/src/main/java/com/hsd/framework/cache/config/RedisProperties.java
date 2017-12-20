package com.hsd.framework.cache.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
	private int database = 0;
	private int secondaryDatabase = 1;
	private String url;
	private String host = "localhost";
	private String password;
	private int port = 6379;
	private boolean ssl;
	private int timeout;
	private int sessionExpire;
	private Pool pool;
	private List<String> idGenNodes;
	private Cluster cluster;
	@Getter
	@Setter
	public static class Cluster {
		private boolean enable;
		private List<String> nodes;
	}
	@Getter
	@Setter
	public static class Pool {
		private int maxIdle = 8;
		private int minIdle = 0;
		private int maxActive = 8;
		private int maxWait = -1;
	}
	public Cluster getCluster(){
		if(cluster==null) cluster=new Cluster();
		return cluster;
	}
}
