package com.hamza.bitirme.model;



import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

public class GirisKullanici {
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private String email;
	
	@Column(length = 200, nullable = false)
	@Getter
	@Setter
	private String sifre;

}
