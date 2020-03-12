package com.apuntesdatajpa.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apuntesdatajpa.springboot.app.models.dao.IClienteDao;
import com.apuntesdatajpa.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {
	
	// Inyectamos la interfaz, donde la clase implementClienteDao implementa esta interfaz, esto ya lo vimos antes
	// y de porque lo colocabamos así. Donde busca un bean que tenga implementada esa interface.
	// @Qualifier(en el caso de que no creemos una clase @Configuration), para especificar cual bean se va a inyectar. 
	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;
	

	@RequestMapping("/")
	public String index() {
		
		return "redirect:listar";
	}
	

	
	@RequestMapping(value="listar", method=RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de clientes");
		// Llamamos el método findAll(), donde tenemos todas las tuplas de la tabla clientes que está en una lista
		model.addAttribute("clientesLista", clienteDao.findAll());
		
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crearTupla(Map<String, Object> model) {
		
		// Command Object, para el formulario
		Cliente cliente = new Cliente();
		
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		
		return "formTupla";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardarTupla(@Valid Cliente cliente, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario de Cliente");
			
			return "formTupla";
			
		}
		
		clienteDao.save(cliente);
		
		return "redirect:listar";
		
	}
	
	// EDITAR
	
	@RequestMapping(value="/form/{id}")
	public String editarTupla(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			
			cliente = clienteDao.findOne(id);
		} else {
			
			return "redirect:listar";
		}
		
		// Si el id, es menor que cero, enviara un NULL, sino enviará el objeto @Entity
		model.put("cliente", cliente);
		
		model.put("titulo", "Formulario de Cliente EDITAR");
		
		return "form";
		
	}
	
}
