package com.codingdojo.villageandvillagers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.villageandvillagers.models.Role;
import com.codingdojo.villageandvillagers.repositorys.RoleRepository;

@Service
public class RoleService {

	private static RoleRepository roleRepo;

	public RoleService(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	public Role getOne(Long id) {
		Optional<Role> role = roleRepo.findById(id);
		return role.isPresent() ? role.get() : null;
	}

	public List<Role> getAll() {
		return (List<Role>) roleRepo.findAll();
	}

	public Role create(Role dojo) {
		return roleRepo.save(dojo);
	}

	public Role update(Role dojo) {
		return roleRepo.save(dojo);
	}

	public void delete(Long id) {
		roleRepo.deleteById(id);
	}

}
