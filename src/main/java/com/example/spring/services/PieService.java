package com.example.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ResourceNotFoundException;
import com.example.spring.models.Pie;
import com.example.spring.repositories.PieRepository;

import java.util.List;
import java.util.ArrayList;
// import java.util.Random;

@Service
public class PieService {
	
	private PieRepository pieRepository;

	@Autowired
	public PieService(PieRepository pieRepository) {
		this.pieRepository = pieRepository;
	}

	// private List<Pie> pieList = new ArrayList<>();

	// {
	// 	Pie pie1 = new Pie("Cherry", 6, 800);
	// 	Pie pie2 = new Pie("Apple", 3, 700);
	// 	Pie pie3 = new Pie("BootPie", 8, 10000);

	// 	pieList.add(pie1);
	// 	pieList.add(pie2);
	// 	pieList.add(pie3);
	// }

	// public Pie getRandomPie() {
	// 	int randomInt = new Random().nextInt(pieList.size());
	// 	return pieList.get(randomInt);
	// }

	public List<Pie> getPieList() {
		// return pieList;
		return (List<Pie>) pieRepository.findAll();
	}

	public Pie findPie(String pieName) throws ResourceNotFoundException {
		// for(Pie pie:pieList) {
		// 	if(pie.getName().equals(pieName)) return pie;
		// }
		return pieRepository.findById(pieName)
				.orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name or try another."));
	}

	// TODO: Create custom query
	public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException {
		List<Pie> caloriePieList = pieRepository.findByCaloriesLessThan(limit);
		// List<Pie> caloriePieList = new ArrayList<>();
		// for(Pie pie:pieList) {
		// 	if(pie.getCalories() <= limit) {
		// 		caloriePieList.add(pie);
		// 	}
		// }
		// if(caloriePieList.isEmpty()) throw new ResourceNotFoundException("No pies exist with calories equal to or lower than " + limit);
		return caloriePieList;
	}


	public void deletePie(String pieName) { 
		// pieList.removeIf(pie -> pie.getName().equals(pieName)); 
		pieRepository.deleteById(pieName);
	}

	public void patchPie(String pieName, int calories, int slices) throws ResourceNotFoundException {
		Pie pie = pieRepository.findById(pieName)
					.orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name or try another."));
		
		if(calories > 0) pie.setCalories(calories);
		if(slices > 0) pie.setSlices(slices);
		pieRepository.save(pie);

		// for(Pie pie:pieList) {
		// 	if(pie.getName().equals(pieName)) {
		// 		if(calories > 0) pie.setCalories(calories);
		// 		if(slices > 0) pie.setSlices(slices);
		// 		return;
		// 	}
		// }
		// throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another.");
	}

	public void updatePie(Pie updatedPie) throws ResourceNotFoundException {
		Pie pie = pieRepository.findById(updatedPie.getName())
					.orElseThrow(() -> new ResourceNotFoundException(updatedPie.getName() + " was not found. Please check name or try another."));
		pieRepository.save(pie);
		// if(pieList.removeIf(pie -> pie.getName().equals(updatedPie.getName()))) {
		// 	pieList.add(updatedPie);
		// 	return;
		// }
		// throw new ResourceNotFoundException(updatedPie.getName() + " was not found. Please check name or try another.");
	}

	public void addNewPie(Pie newPie) { 
		// pieList.add(newPie); 
		pieRepository.save(newPie);
	}




}
