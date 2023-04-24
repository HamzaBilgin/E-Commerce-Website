package com.hamza.bitirme.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamza.bitirme.model.KayitUser;
import com.hamza.bitirme.model.Kullanici;
import com.hamza.bitirme.service.KullaniciService;

import lombok.Data;


@Data
@Controller
public class KullaniciKayitController {

	
	@Autowired
	private KullaniciService kullaniciService;
	
	
	private Kullanici kullanici;
	
	@GetMapping("/kayit")
	public String showForm(Model model) {
		KayitUser kullanici = new KayitUser();
		model.addAttribute("kullanici", kullanici);
	
		return "Kayit.html";
	}
	

	@PostMapping("/kayit")
	public String submitForm(@RequestBody Kullanici kullanici) {
	
	
				kullaniciService.kullaniciKaydet(kullanici);
			
		return "Kayit.html";
	}
	
	@RequestMapping("/email")
	public String showForm(@RequestBody String email) {
		System.out.println("email geldi" + email);
		System.out.println("sifre : " + kullaniciService.kullaniciKayıtlı(email));
		return kullaniciService.kullaniciKayıtlı(email);
	}
	
	
	
}
