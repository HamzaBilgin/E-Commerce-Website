package com.hamza.bitirme.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hamza.bitirme.model.Kullanici;
import com.hamza.bitirme.service.KullaniciService;

import lombok.Data;


@Data
@Controller
public class KullaniciGirisController {
	@Autowired
	private KullaniciService kullaniciService;
	

	
	private Kullanici kullanici;
	
	@GetMapping("/giris")
	public String showForm(Model model) {
		Kullanici kullanici = new Kullanici();
		model.addAttribute("kullanici", kullanici);	
		return "Giris.html";
	}

	@PostMapping("/giris")
	public String submitForm(@ModelAttribute("kullanici") Kullanici kullanici) {
		if(girisKontrol(kullanici)==true) {
			return "index.html";
		}
		return "Giris.html";
	}
	
	@PostMapping("/girisKontrol")
	public boolean girisKontrol( Kullanici kullanici) {
		
			if(kullaniciService.kullaniciKayıtKontrol(kullanici.getEmail()) == true) {

				if(kullaniciService.secilenKullaniciSifre(kullanici.getEmail()).equals(kullanici.getSifre())) {
					return true;
				}
				else {
					return false;
				}
				
			}
			else {
				return false;
			}
	
	}
	
	

}
