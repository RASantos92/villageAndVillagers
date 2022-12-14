package com.codingdojo.villageandvillagers.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.villageandvillagers.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
