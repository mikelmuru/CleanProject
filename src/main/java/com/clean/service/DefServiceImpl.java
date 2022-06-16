package com.clean.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clean.entity.DefClass;
import com.clean.entity.DefClassDTO;
import com.clean.repository.DefRepository;

@Service
public class DefServiceImpl implements DefService {
	
	@Autowired
	private DefRepository myrepository;
	
	private static Logger log = LoggerFactory.getLogger(DefServiceImpl.class);
	
	private DefClass error = new DefClass(-1,"Wrong request. Try again.");

	@Override
	public ResponseEntity<DefClass> save(DefClassDTO mydto) {
		
		log.info("Entro en save");
		if (mydto.getText().isBlank()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(error);
		}
		
		DefClass myclass = new DefClass();
		myclass.setText(mydto.getText());
		if (mydto.getId() >= 0) { 
			myclass.setId(mydto.getId());
		}
		log.info(myclass.getText());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(myrepository.save(myclass));
	}

	@Override
	public ResponseEntity<List<DefClass>> getAll() {
		return ResponseEntity.ok(myrepository.findAll());
	}

	@Override
	public ResponseEntity<DefClass> update(DefClassDTO mydto) {
		
		if (!myrepository.findById(mydto.getId()).isPresent()) {
			return ResponseEntity
					.status(404)
					.body(null);
		}
		
		DefClass updatedata = new DefClass();
		updatedata.setId(mydto.getId());
		updatedata.setText(mydto.getText());
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(myrepository.save(updatedata));
	}

	@Override
	public ResponseEntity<HttpStatus> deleteById(int id) {
		if (!myrepository.findById(id).isPresent()) {
			return ResponseEntity
					.status(404)
					.body(null);
		}
		
		myrepository.deleteById(id);
		return ResponseEntity
				.ok(null);
	}

}
