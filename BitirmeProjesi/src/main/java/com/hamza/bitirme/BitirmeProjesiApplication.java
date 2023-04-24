package com.hamza.bitirme;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableCaching
public class BitirmeProjesiApplication extends CachingConfigurerSupport{

	public static void main(String[] args) {
		SpringApplication.run(BitirmeProjesiApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cM = new SimpleCacheManager();
		Cache kullaniciCache = new ConcurrentMapCache("kullanici_listesi");
		Cache kullanicilarCache = new ConcurrentMapCache("kullanicilar");
		
		Cache urunlerCache = new ConcurrentMapCache("urunler_listesi");	
		Cache urunCache = new ConcurrentMapCache("urunler");
		
		cM.setCaches(Arrays.asList(urunlerCache,urunCache,kullanicilarCache,kullaniciCache));
		return cM;
	}
}
