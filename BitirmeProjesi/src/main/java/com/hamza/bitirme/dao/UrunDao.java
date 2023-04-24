package com.hamza.bitirme.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hamza.bitirme.model.Urun;




@Repository
public interface UrunDao extends JpaRepository<Urun, Long> {

	
	@Query("SELECT u FROM Urun u WHERE u.urunId = ?1")
	public Urun urunBul(Long urunId);
	
	
	@Query("SELECT u FROM Urun u WHERE u.urunId = :urunIdName")
	public Urun urunBulByParName(@Param("urunIdName") Long urunId );
	
	
	@Query("SELECT u FROM Urun u WHERE u.urunId IN :urunIdleriListesi")
	public List<Urun> urunBulGelenIdlerIle(@Param("urunIdleriListesi") Collection<Long> urunIdleri);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Modifying
	@Query("UPDATE Urun u SET u.urunaciklama = :aciklamaDegeri WHERE u.urunId = :urunIdPar")
	public Integer guncelleUrunAciklama(@Param("aciklamaDegeri") String urunaciklama, @Param("urunIdPar") Long urunId);
	
	
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO Urun (urunadi, urunaciklama) VALUES(:adParametresi, :aciklamaParametresi)")
	public void urunKaydet(@Param("adParametresi") String urunadi, @Param("aciklamaParametresi") String urunaciklama);
	
	
	
}
