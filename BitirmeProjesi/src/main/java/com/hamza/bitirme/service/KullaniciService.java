package com.hamza.bitirme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hamza.bitirme.dao.KullaniciDao;
import com.hamza.bitirme.model.Kullanici;

import lombok.Getter;
import lombok.Setter;

@Service
public class KullaniciService {

	@Autowired
	@Getter
	@Setter
	private KullaniciDao kullaniciDao;

	// Kullanıyı Kayıt eder
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(allEntries = true, cacheNames = { "kullanici_listesi", "kullanicilar" })
	public Kullanici kullaniciKaydet(Kullanici kullanici) {
		return kullaniciDao.save(kullanici);
	}

	// Kullaniciların hepsini getirir
	@Cacheable(value = "kullanici_listesi")
	public List<Kullanici> kullaniciListesiniVer() {
		return kullaniciDao.findAll();
	}

	// İd ile kullanıcıyı bulup getirir.
	@CachePut(value = "kullanicilar", key = "#kullaniciId")
	public Kullanici secilenKullanici(Long kullaniciId) {
		return kullaniciDao.kullaniciBul(kullaniciId);
	}

	// KullaniId si ile kullaniciyi siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#kullaniciId", allEntries = true, cacheNames = { "kullanici_listesi", "kullanicilar" })
	public Boolean secilenKullaniciSil(Long kullaniciId) {
		kullaniciDao.deleteById(kullaniciId);
		return true;
	}

	// Bütün kullanıcıları siler
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@CacheEvict(key = "#kullaniciId", allEntries = true, cacheNames = { "kullanici_listesi", "kullanicilar" })
	public Boolean allKullaniciSilServis() {
		kullaniciDao.deleteAll();
		;
		return true;
	}

	// Email e göre kullanici sifresini getirir.
	public String secilenKullaniciSifre(String email) {
		return kullaniciDao.kullaniciSifreGetir(email);
	}

	// Kullanici emailine göre kayıtlı kullanıcı var mı yok mu ona bakar. Var ise
	// true döner yok ise false döner
	public boolean kullaniciKayıtKontrol(String email) {
		if (kullaniciDao.kullaniciSifreGetir(email) != null) {
			return true;
		} else {
			return false;
		}

	}

	// Kullanici emailine göre kayıtlı kullanıcı var mı yok mu ona bakar. Var ise
	// true döner yok ise false döner
	public String kullaniciKayıtlı(String email) {
		if (kullaniciDao.kullaniciSifreGetir(email) != null) {
			return email;
		} else {
			return null;
		}

	}

	// Kullanici emailine göre kayıtlı kullanıcı var mı yok mu ona bakar. Var ise
	// true döner yok ise false döner
	public boolean userGirisKontrol(String email) {
		if (kullaniciDao.kullaniciSifreGetir(email) != null) {
			return true;
		} else {
			return false;
		}

	}

	public Kullanici kullaniciKayıtlıEmailIle(String email) {

		return kullaniciDao.kullaniciBulEmail(email);
	}

}
