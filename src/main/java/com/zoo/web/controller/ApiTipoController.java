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


import com.zoo.web.DAO.TipoDAO;
import com.zoo.web.entity.Animal;
import com.zoo.web.entity.Tipo;

@CrossOrigin
@RestController
@RequestMapping("/apitipo")
public class ApiTipoController {

	@Autowired
	
	private TipoDAO tDAO;
	
	@GetMapping("/tipo")
	public Iterable<Tipo> listarTipo() {
		
		return tDAO.crud().findAll();
	}
	
	@PostMapping("/tipo")
	public ResponseEntity<Tipo> guardar(
			@RequestBody Tipo tipo) {
		
		try {
			
			Tipo tipoCreado
					= tDAO.crud().save(tipo);
			
			return new ResponseEntity<Tipo>(tipoCreado, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/tipo/{id}")
	public ResponseEntity<Map<String, String>> eliminar(
			@PathVariable("id") int id) {
		
		
		try {
			tDAO.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@GetMapping("/tipo/{id}")
	public ResponseEntity<Tipo> buscarTipo(@PathVariable("id") int id){
		
		Tipo tipo =null;
		try {
			tipo = tDAO.crud().findById(id).get();
			return new ResponseEntity<Tipo>(tipo, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}	
	}
	
	@PutMapping("/tipo/{id}")
	public ResponseEntity<Tipo> modificar(
			@RequestBody Tipo tipo,
			@PathVariable("id") int id) {
		
		Tipo tipoBuscado = null;
		
		try {
			tipoBuscado = tDAO.crud().findById(id).get();
			//si lo encuentra le pasamos los nuevos datos
			tipoBuscado.setNombre(tipo.getNombre());
			tipoBuscado.setDescripcion(tipo.getDescripcion());
			
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			Tipo tipoModificado
					= tDAO.crud().save(tipoBuscado);
			return new ResponseEntity<Tipo>(tipoModificado,
					HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
}
