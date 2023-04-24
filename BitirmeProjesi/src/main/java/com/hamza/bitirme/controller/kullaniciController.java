package com.hamza.bitirme.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hamza.bitirme.model.GirisKullanici;
import com.hamza.bitirme.model.Kullanici;
import com.hamza.bitirme.service.KullaniciService;

import lombok.Data;

@Controller("userController")
@Data
@Scope("request")
public class kullaniciController implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private List<Kullanici> kullaniciListesi;
	
	private String girisSifreDeğeri;
	@Autowired
	private KullaniciService kullaniciService;
	
	

	private Kullanici kullanici;
	
	private GirisKullanici girisKullanici;
	
	private Kullanici secilenKullanici;

	@PostConstruct
	public void init() {
		setKullanici(new Kullanici());
	
		setKullaniciListesi(kullaniciService.kullaniciListesiniVer());
	}
	
	public void yeniKullanici() {
		this.kullanici = new Kullanici();
	}
	public void secilenKullaniciGoster() {
		setKullanici(getSecilenKullanici());
		
	}
	public void kullaniciKaydet() {
		
		if(kullaniciService.kullaniciKayıtKontrol(kullanici.getEmail()) == false) {
		
			try {
				this.kullaniciService.kullaniciKaydet(this.kullanici);
				setKullaniciListesi(kullaniciService.kullaniciListesiniVer());
			
			
			} catch (Exception e) {
				

			}
		}
		else {
		
		}

	}
	
	public void kullaniciLogin() {
		
		try {
		
			if(kullaniciService.secilenKullaniciSifre(kullanici.getEmail()).equals(kullanici.getSifre())){
			
			
			}
			else {
				
		
			}
		} catch (Exception e) {
		

		}
	}


}
