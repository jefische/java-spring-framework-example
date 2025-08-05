package com.example.spring.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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

	@GetMapping(params = "pieName")
	public @ResponseBody ResponseEntity<Pie> getPie(@RequestParam String pieName) {
		return new ResponseEntity<>(pieService.findPie(pieName), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("calories/{limit}")
	public @ResponseBody ResponseEntity<List<Pie>> findPiesByCalories(@PathVariable int limit) {
		List<Pie> caloriePieList = pieService.getPiesByCalories(limit);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(caloriePieList);
	}

	@DeleteMapping("delete/{pieName}")
	public @ResponseBody ResponseEntity<String> deletePie(@PathVariable String pieName) {
		pieService.deletePie(pieName);
		return ResponseEntity.accepted()
				.body("Successfully deleted");
	}

	@PatchMapping("patch")
	public @ResponseBody ResponseEntity<String> patchPie(@RequestParam String pieName,
														 @RequestParam(defaultValue = "0", required = false) int calories,
														 @RequestParam(defaultValue = "0", required = false) int slices) {
			pieService.patchPie(pieName, calories, slices);
			return ResponseEntity.ok().body("Pie Successfully Patched");
	}

	@PutMapping("update")
	public @ResponseBody ResponseEntity<String> updatePie(@RequestBody Pie updatedPie) {
		pieService.updatePie(updatedPie);
		return ResponseEntity.ok()
				.body("Pie Successfully Updated");
	}

	@PostMapping("create")
	public @ResponseBody ResponseEntity<Pie> createPie(@RequestBody Pie newPie) {
		pieService.addNewPie(newPie);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(newPie);
	}

}
