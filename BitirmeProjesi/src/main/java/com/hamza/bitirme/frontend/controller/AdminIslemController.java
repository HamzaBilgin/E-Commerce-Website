package com.hamza.bitirme.frontend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hamza.bitirme.model.Urun;
import com.hamza.bitirme.service.UrunService;

import lombok.Data;


@Data
@Controller
public class AdminIslemController {
	
	 private final String UPLOAD_DIR = "./uploads/";
	 
	@Autowired
	private UrunService urunService;
	
	
	private Urun urun;
	
	//Admin ana sayfası
	@RequestMapping("/admin")
	public String showAdmin() throws IOException {
		
	  
		return "admin/admin.html";
	}

	// Admin urun kaydet sayfasi
	@RequestMapping("/admin/urunkaydet")
	public String showForm(Model model) throws IOException {
		Urun urun = new Urun();
		
		  System.out.println("Get");
		
		model.addAttribute("urun", urun);
	  
		return "admin/urunKaydet.html";
	}

	@PostMapping("/admin/urunkaydet")
	public String submitForm(@RequestParam("file") MultipartFile file,@ModelAttribute("urun") Urun urun) throws IOException {
		
		String fileName = file.getOriginalFilename();
		  urun.setUrunresimad(fileName);
		  urun.setUrunresim(file.getBytes());
		  urun.setUrunresimsize(file.getSize());
	
		this.urunService.urunKaydet(urun);
		return "admin/urunKaydet.html";
	}
	//Urun BİLGİLERİ
		@RequestMapping("/admin/urunBilgileri")
		public String showUrun() throws IOException {
			
		  
			return "admin/urunBilgileri.html";
		}
		
	//Kullanıcı Bİlgileri
	@RequestMapping("/admin/kullaniciBilgileri")
	public String showKullanici() throws IOException {
		
	  
		return "admin/kullaniciBilgileri.html";
	}
	

}
