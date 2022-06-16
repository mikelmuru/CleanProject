package com.clean.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clean.entity.DefClass;
import com.clean.entity.DefClassDTO;
import com.clean.repository.DefRepository;

class DefServiceImplTest {
	
	@Mock
	private DefRepository repository;
	
	@InjectMocks
	private DefServiceImpl service;
	
	private DefClassDTO mySaveDTO = new DefClassDTO();
	private DefClass mySaved = new DefClass(1, "Hello Wrold");
	private DefClass mySaved2 = new DefClass(2, "Hello World 2");
	private DefClassDTO updateDTO = new DefClassDTO(1, "Hello World 1 updated");
	private DefClass myUpdate = new DefClass(1, "Hello Wrold 1 updated");
	private List<DefClass> listAll = new ArrayList<DefClass>();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mySaveDTO.setText("Hello World");
		listAll.add(mySaved);
		listAll.add(mySaved2);
		
		when(repository.findById(1)).thenReturn(Optional.of(mySaved));
		when(repository.findAll()).thenReturn(listAll);
	}

	@Test
	void testSave() {
		when(repository.save(any())).thenReturn(mySaved);
		
		ResponseEntity<?> saveEntity = service.save(mySaveDTO);
		
		assertEquals(HttpStatus.CREATED, saveEntity.getStatusCode());
		assertEquals(mySaved, saveEntity.getBody());
	}

	@Test
	void testGetAll() {
		ResponseEntity<?> getAll = service.getAll();
		
		assertEquals(HttpStatus.OK, getAll.getStatusCode());
		assertEquals(listAll, getAll.getBody());
	}

	@Test
	void testUpdate() {
		when(repository.save(any())).thenReturn(myUpdate);
		
		ResponseEntity<?> saveEntity = service.save(updateDTO);
		
		assertEquals(HttpStatus.CREATED, saveEntity.getStatusCode());
		assertEquals(myUpdate, saveEntity.getBody());
	}

	@Test
	void testDeleteById() {
		ResponseEntity<?> delete = service.deleteById(mySaved.getId());
		
		assertEquals(HttpStatus.OK, delete.getStatusCode());
	}

}
