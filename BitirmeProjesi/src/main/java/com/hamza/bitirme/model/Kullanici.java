package com.hamza.bitirme.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kullanici")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE Kullanici SET deleted = true WHERE kullaniciId = ?")
@Where(clause = "deleted=false")
public class Kullanici extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	@Column(name = "KULLANICIID")
	private long kullaniciId;
	
	@Column(length = 200, nullable = false, name = "ad")
	@Getter
	@Setter
	private String ad;
	
	@Column(length = 200, nullable = false, name = "soyad")
	@Getter
	@Setter
	private String soyad;
	
	@Column(length = 200, nullable = false, name = "email")
	@Getter
	@Setter
	private String email;
	
	@Column(length = 200, nullable = false, name = "sifre")
	@Getter
	@Setter
	private String sifre;
	
	

}
