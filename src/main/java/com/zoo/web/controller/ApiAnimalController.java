package com.zoo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.web.DAO.AnimalDAO;
import com.zoo.web.DAO.SectorDAO;
import com.zoo.web.DAO.TipoDAO;
import com.zoo.web.entity.Animal;
import com.zoo.web.entity.Sector;
import com.zoo.web.entity.Tipo;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiAnimalController {

	@Autowired
	private AnimalDAO aDAO;
	private TipoDAO tDAO;
	private SectorDAO sDAO;
	
	@GetMapping("/animal")
	public Iterable<Animal> listar() {
		
		return aDAO.crud().findAll();
	}
	
	
	
	
	
	
	@PostMapping("/animal")
	public ResponseEntity<Animal> guardar(
			@RequestBody Animal animal) {
		
		try {
			
			Animal animalCreado
					= aDAO.crud().save(animal);
			
			return new ResponseEntity<Animal>(animalCreado, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@PutMapping("/animal/{id}")
	public ResponseEntity<Animal> modificar(
			@RequestBody Animal animal,
			@PathVariable("id") int id) {
		
		Animal animalBuscado = null;
		
		try {
			animalBuscado = aDAO.crud().findById(id).get();
			//si lo encuentra le pasamos los nuevos datos
			animalBuscado.setNombre(animal.getNombre());
			animalBuscado.setPeso(animal.getPeso());
			animalBuscado.setTipo(animal.getTipo());
			animalBuscado.setGenero(animal.getGenero());
			animalBuscado.setFechaNacimiento(animal.getFechaNacimiento());
			animalBuscado.setSector(animal.getSector());
			animalBuscado.setFechaIngreso(animal.getFechaIngreso());
            animalBuscado.setFechaDefuncion(animal.getFechaDefuncion());
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			Animal animalModificado
					= aDAO.crud().save(animalBuscado);
			return new ResponseEntity<Animal>(animalModificado,
					HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
	
	@GetMapping("/animal/{id}")
	public ResponseEntity<Animal> buscarAnimal(@PathVariable("id") int id){
		
		Animal animal =null;
		try {
			animal = aDAO.crud().findById(id).get();
			return new ResponseEntity<Animal>(animal, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}	
	}
	
	@DeleteMapping("/animal/{id}")
	public ResponseEntity<Map<String, String>> eliminar(
			@PathVariable("id") int id) {
		
		
		try {
			aDAO.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
	}
	
	
	
	
}