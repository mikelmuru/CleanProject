package com.clean.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.entity.DefClass;
import com.clean.entity.DefClassDTO;
import com.clean.service.DefServiceImpl;

@RestController
@RequestMapping("/default")
public class DefController {
	
	@Autowired
	private DefServiceImpl myservice;
	
	private static Logger log = LoggerFactory.getLogger(DefController.class);
	
	@GetMapping("/public")
	public String helloPublic() {
		return "Hello World!";
	}
	
	@PostMapping("/private")
	public ResponseEntity<DefClass> save(@RequestBody DefClassDTO mydto){
		log.info("Metodo save (CONTROLLER).");
		
		return myservice.save(mydto);
	}
	
	@GetMapping("/private")
	public ResponseEntity<List<DefClass>> getAll(){
		log.info("Metodo getAll (CONTROLLER).");
		
		return myservice.getAll();
	}
	
	@PutMapping("/private")
	public ResponseEntity<DefClass> update(DefClassDTO mydto){
		log.info("Metodo update (CONTROLLER8).");
		
		return myservice.update(mydto);
	}

	@DeleteMapping("/private/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable int id){
		log.info("Metodo deleteById (CONTROLLER).");
		
		return myservice.deleteById(id);
	}
	
}
