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
import com.hamza.bitirme.model.Urun;
import com.hamza.bitirme.service.UrunService;



@RestController
@RequestMapping("/rest/urun")
public class UrunRestController {

	@Autowired
	private UrunService urunService;
	
	//Ürünleri Listeler
	@GetMapping("/list")
	public List<Urun> urunListesiniVer() {

		return urunService.urunlistesiniVer();
	}
	
	@GetMapping("/select/{urunId}")
	public Urun urunuVer(@PathVariable(name = "urunId", required = true) Long urunId) {
		System.out.println("aaa" + urunId);
		System.out.println("bbb"  + urunService.secilenUrunuVer(urunId));
		return urunService.secilenUrunuVer(urunId);			
		
	}
	
	
	@PostMapping("/save")
	public Urun urunKaydet(@RequestBody Urun urun) {
		
		return urunService.urunKaydet(urun);
	}
	
	@PutMapping("/update/{urunId}")
	public Urun urunGuncelle(@RequestBody Urun urun, @PathVariable(name = "urunId", required = true ) Long urunId) {
		
		Urun savedUrun = urunService.secilenUrunuVer(urunId);
		
		savedUrun.setUrunaciklama(urun.getUrunaciklama());
		savedUrun.setUrunadi(urun.getUrunadi());
		savedUrun.setUrunfiyat(urun.getUrunfiyat());
		
		return urunService.urunKaydet(savedUrun);			
	}
	
	@DeleteMapping("/delete/{urunId}")
	public Boolean urunSil(@PathVariable(required = true) Long urunId) {
		
		return urunService.secilenUrunuSil(urunId) ? true:false;
		
	}
	
	
	

}
