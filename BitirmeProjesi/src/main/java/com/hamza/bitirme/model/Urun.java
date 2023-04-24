package com.hamza.bitirme.model;




import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "urunn")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE Urun SET deleted = true WHERE urunId = ?")
@Where(clause = "deleted=false")
public class Urun extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	@Column(name = "URUNID")
	private long urunId;
	
	@Column(length = 200, name = "urunadi")
	@Getter
	@Setter
	private String urunadi;
	
	@Column(length = 200, name = "uruntipi")
	@Getter
	@Setter
	private String uruntipi;
	
	@Column(length = 2500, name = "urunaciklama")
	@Getter
	@Setter
	private String urunaciklama;
	
	@Column(name = "urunfiyat")
	@Getter
	@Setter
	private Double urunfiyat;

	@Lob
	@Basic(fetch = FetchType.LAZY)//Lazy mi Eager mı ayarlanabilir.
	@Getter
	@Setter
	@Column(name = "urunresim")
	private byte[] urunresim;
	
	@Getter
	@Setter
	@Column(length = 2500,name = "urunresimad")
	private String urunresimad;
	
	
	@Getter
	@Setter
	@Column(name = "urunresimsize")
	private long urunresimsize;
	
	
	

}
