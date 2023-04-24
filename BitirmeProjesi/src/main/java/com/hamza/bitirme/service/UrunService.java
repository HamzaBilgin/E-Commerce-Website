package com.hamza.bitirme.service;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hamza.bitirme.dao.UrunDao;
import com.hamza.bitirme.model.Urun;

import lombok.Getter;
import lombok.Setter;

@Service
//@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
public class UrunService {

	@Autowired
	@Getter
	@Setter
	private UrunDao urunDao;
	
	//Ürünleri Daodan getirir.
	@Transactional(readOnly = true)
	@Cacheable(value = "urunler_listesi")
	public List<Urun> urunlistesiniVer() {
		return urunDao.findAll();
	}

	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Transactional( isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "urunler_listesi", "urunler" })
	public Urun urunKaydet(Urun urun) {
		return urunDao.save(urun);
	}

	
	@CachePut(value = "urunler", key = "#urunId")
	public Urun secilenUrunuVer(Long urunId) {
		
		return urunDao.urunBul(urunId);
	}

	@Lock(LockModeType.PESSIMISTIC_WRITE) 
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#urunId", allEntries = true, cacheNames = { "urunler_listesi", "urunler" })
	public Boolean secilenUrunuSil(Long urunId) {
		System.out.println("s - urunId " + urunId);
		
		urunDao.deleteById(urunId);
		return true;
	}

}
