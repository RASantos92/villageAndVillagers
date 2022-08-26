package com.codingdojo.villageandvillagers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.villageandvillagers.models.Village;
import com.codingdojo.villageandvillagers.repositorys.VillageRepository;

@Service
public class VillageService {

	private static VillageRepository villageRepo;

	public VillageService(VillageRepository villageRepo) {
		this.villageRepo = villageRepo;
	}

	public Village getOne(Long id) {
		Optional<Village> village = villageRepo.findById(id);
		return village.isPresent() ? village.get() : null;
	}

	public List<Village> getAll() {
		return (List<Village>) villageRepo.findAll();
	}

	public Village create(Village dojo) {
		return villageRepo.save(dojo);
	}

	public Village update(Village dojo) {
		return villageRepo.save(dojo);
	}

	public void delete(Long id) {
		villageRepo.deleteById(id);
	}

}
