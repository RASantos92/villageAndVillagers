package com.codingdojo.villageandvillagers.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.villageandvillagers.models.Village;

@Repository
public interface VillageRepository extends CrudRepository<Village, Long> {

}
