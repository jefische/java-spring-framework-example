package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.services.PieService;
import com.example.spring.models.Pie;
import java.util.List;

@Controller
@RequestMapping("pie")
public class PieController {

	private PieService pieService;

	public PieController(PieService pieService) {
		this.pieService = pieService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Pie> getPieList(){
		return pieService.getPieList();
	}
	
}
