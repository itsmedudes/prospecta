package com.prospecta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospecta.Model.EntryEntity;


public interface EntryEntityRepo extends JpaRepository<EntryEntity, String>{
	
	public Boolean existsByApi(String api);
	
	public Boolean existsByLink(String link);
}
