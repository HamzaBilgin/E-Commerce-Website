package com.hamza.bitirme.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hamza.bitirme.model.Kullanici;
import com.hamza.bitirme.service.KullaniciService;




@RestController
@RequestMapping("/rest/kullanici")
public class KullaniciRestController {
	@Autowired
	private KullaniciService kullaniciService;

	//Bütün Kullaniciları Getirir
	@GetMapping("/getKullanicilar")
	private List<Kullanici> kullaniciListesiniVer() {
		
		return kullaniciService.kullaniciListesiniVer();
	}
	
	//İD ye göre kullanici getirir
	@GetMapping("/getKullaniciById/{kullaniciId}")
	private Kullanici kullaniciById(
			@PathVariable(name = "kullaniciId", required = true) Long kullaniciId) {
		return kullaniciService.secilenKullanici(kullaniciId);
	}
	
	//Yeni kullanici kaydeder
	@PostMapping("/postKullaniciKaydet")
	public Kullanici kullaniciKaydet(@RequestBody Kullanici kullanici) {
		return kullaniciService.kullaniciKaydet(kullanici);
		
	}
	
	//Kullanici Bilgilerini değiştirir
	@PutMapping("/putKullaniciDegistirById/{kullaniciId}")
	public Kullanici kullaniciGuncelle(@RequestBody Kullanici kullanici,
			@PathVariable(name = "kullaniciId", required = true) Long kullaniciId) {
		
		Kullanici savedKullanici = kullaniciService.secilenKullanici(kullaniciId);

		savedKullanici.setAd(kullanici.getAd());
		savedKullanici.setSoyad(kullanici.getSoyad());
		savedKullanici.setEmail(kullanici.getEmail());
		savedKullanici.setSifre(kullanici.getSifre());
		
		return kullaniciService.kullaniciKaydet(savedKullanici);
	}
	
	//İd ye göre kullanici siler
	@DeleteMapping("/silKullaniciById/{kullaniciId}")
	public Boolean kullaniciSil(@PathVariable(name = "kullaniciId", required = true) Long kullaniciId) {
		
		return kullaniciService.secilenKullaniciSil(kullaniciId) ? true:false;
		
	}
	
	//Bütün Kullanicilari siler
	@RequestMapping("/deleteKullanici")
	public Boolean allKullaniciSil() {		
		return kullaniciService.allKullaniciSilServis() ? true:false;
	}
	//email ile kullanıcı çağrısı yapar
	@RequestMapping("/emailIle")
	public Kullanici showForm2(@RequestBody Kullanici kullanici) {
		System.out.println("email geldi" + kullanici.getEmail());
		System.out.println("sifre : " + kullaniciService.kullaniciKayıtlı((kullanici.getEmail())));
		return kullaniciService.kullaniciKayıtlıEmailIle(kullanici.getEmail());
	}
	
	//email ile kullanıcı sifre çağrısı yapar
		@RequestMapping("/emailIleSifre")
		public Kullanici showForm3(@RequestBody Kullanici kullanici) {
			
			
			return kullaniciService.kullaniciKayıtlıEmailIle(kullanici.getEmail());
		}

	
}
