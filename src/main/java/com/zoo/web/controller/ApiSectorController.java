package com.zoo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.zoo.web.DAO.SectorDAO;
import com.zoo.web.entity.Sector;


@CrossOrigin
@RestController
@RequestMapping("/apisector")
public class ApiSectorController {

	@Autowired
	
	private SectorDAO sDAO;
	@GetMapping("/sector")
	public Iterable<Sector> listarSector() {
		
		return sDAO.crud().findAll();	
	}	
}
