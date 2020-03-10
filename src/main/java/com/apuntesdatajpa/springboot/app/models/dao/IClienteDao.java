package com.apuntesdatajpa.springboot.app.models.dao;

import java.util.List;

import com.apuntesdatajpa.springboot.app.models.entity.Cliente;

// Interface con los métodos que nos permitirán manipular los datos
public interface IClienteDao {
	
	// métodos que implementaremos en la clase @Repository
	public List<Cliente> findAll();
	
	

}
