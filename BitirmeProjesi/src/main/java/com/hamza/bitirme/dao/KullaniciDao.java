package com.hamza.bitirme.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hamza.bitirme.model.Kullanici;


@Repository
public interface KullaniciDao extends JpaRepository<Kullanici, Long> {

	@Query("SELECT k FROM Kullanici k WHERE k.kullaniciId = :kullaniciIdAd")
	public Kullanici kullaniciBulAdIle(@Param("kullaniciIdAd") Long KullaniciId);
	
	//ID ile kullaniciyi bulur sonucu kullanıcı olur
	@Query("SELECT k FROM Kullanici k WHERE k.kullaniciId = ?1")
	public Kullanici kullaniciBul(Long kullaniciId);
	
	
	//Kullanici ID leri girilerek kullanici listesi oluşturur. Liste döner
	@Query("SELECT k FROM Kullanici k WHERE k.kullaniciId IN :kullaniciIdListesi")
	public List<Kullanici> kullaniciBulGelenIdIle(@Param("kullaniciIdListesi") Collection<Long> kullaniciIdleri);
	
	
	//Kullanıyı kaydeder
	@Query(nativeQuery = true, value = "INSERT INTO Kullanici(ad,soyad,email,sifre) VALUES (:adParametresi,:soyadParametresi,:emailParametresi,:sifreParametresi)")
	public void kullaniciKaydet(@Param("adParametresi") String ad,@Param("soyadParametresi") String soyad,
			@Param("emailParametresi") String email,@Param("sifreParametresi") String sifre);

	
	//Mevcut kullanicinin emailine göre sifresini getirir. 		İstek : email	Sonuç : sifre
	@Query("SELECT sifre FROM Kullanici  WHERE email = :emailParametresi")
	public String  kullaniciSifreGetir(@Param("emailParametresi") String email);
	
	//ID ile kullaniciyi bulur sonucu kullanıcı olur
			@Query("SELECT k FROM Kullanici k WHERE email = :emailParametresi")
			public Kullanici kullaniciBulEmail(@Param("emailParametresi") String email);

}
