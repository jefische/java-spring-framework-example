package com.example.spring.services;

import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ResourceNotFoundException;
import com.example.spring.models.Pie;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Service
public class PieService {
	
	private List<Pie> pieList = new ArrayList<>();

	{
		Pie pie1 = new Pie("Cherry", 800, 6);
		Pie pie2 = new Pie("Apple", 700, 3);
		Pie pie3 = new Pie("BootPie", 10000, 8);

		pieList.add(pie1);
		pieList.add(pie2);
		pieList.add(pie3);
	}

	public Pie getRandomPie() {
		int randomInt = new Random().nextInt(pieList.size());
		return pieList.get(randomInt);
	}

	public List<Pie> getPieList() {
		return pieList;
	}

	public Pie findPie(String pieName) throws ResourceNotFoundException {
		for(Pie pie:pieList) {
			if(pie.getName().equals(pieName)) return pie;
		}
		throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another.");
	}

	public void deletePie(String pieName) { pieList.removeIf(pie -> pie.getName().equals(pieName)); }

	public void patchPie(String pieName, int calories, int slices) throws ResourceNotFoundException {
		for(Pie pie:pieList) {
			if(pie.getName().equals(pieName)) {
				if(calories > 0) pie.setCalories(calories);
				if(slices > 0) pie.setSlices(slices);
				return;
			}
		}
		throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another.");
	}

	public void updatePie(Pie updatedPie) throws ResourceNotFoundException {
		if(pieList.removeIf(pie -> pie.getName().equals(updatedPie.getName()))) {
			pieList.add(updatedPie);
			return;
		}
		throw new ResourceNotFoundException(updatedPie.getName() + " was not found. Please check name or try another.");
	}

	public void addNewPie(Pie newPie) { pieList.add(newPie); }




}
