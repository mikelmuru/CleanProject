package com.clean.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clean.entity.DefClass;
import com.clean.entity.DefClassDTO;

public interface DefService {
	
	public ResponseEntity<DefClass> save(DefClassDTO mydto);
	
	public ResponseEntity<List<DefClass>> getAll();
	
	public ResponseEntity<DefClass> update(DefClassDTO mydto);
	
	public ResponseEntity<HttpStatus> deleteById(int id);

}
