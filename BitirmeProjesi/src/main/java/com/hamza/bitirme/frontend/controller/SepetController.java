package com.hamza.bitirme.frontend.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Data
@Controller
public class SepetController {
	
	@GetMapping("/sepet")
	public String showForm(Model model) throws IOException {
	
		
		  
		
		
		
		
		  
		return "Sepet.html";
	}


}
