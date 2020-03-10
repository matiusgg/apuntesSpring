package com.apuntesdatajpa.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apuntesdatajpa.springboot.app.models.dao.IClienteDao;

@Controller
public class ClienteController {
	
	// Inyectamos la interfaz, donde la clase implementClienteDao implementa esta interfaz, esto ya lo vimos antes
	// y de porque lo colocabamos así. Donde busca un bean que tenga implementada esa interface.
	// @Qualifier(en el caso de que no creemos una clase @Configuration), para especificar cual bean se va a inyectar. 
	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;
	
	/*
	@RequestMapping("/")
	public String index() {
		
		return "redirect:/listar";
	}
	
	*/
	
	@RequestMapping(value="listar", method=RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de clientes");
		// Llamamos el método findAll(), donde tenemos todas las tuplas de la tabla clientes que está en una lista
		model.addAttribute("clientesLista", clienteDao.findAll());
		
		return "listar";
	}
	
}
