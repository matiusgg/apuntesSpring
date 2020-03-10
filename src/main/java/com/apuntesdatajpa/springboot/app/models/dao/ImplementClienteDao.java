package com.apuntesdatajpa.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apuntesdatajpa.springboot.app.models.entity.Cliente;

@Repository("clienteDaoJPA")
public class ImplementClienteDao implements IClienteDao {
	
	// Atributo con la interface que nos ayudará a realizar operaciones sobre las @Entity
	// Le indicamos al EntityManager que se introduzca dentro del contexto de persistencia para gestionar las entidades dentro de este.
	// De manera automatica inyectará el EntityManager dentro del contexto de persistencia, 
	@PersistenceContext
	private EntityManager em;
	
	// Método de la interface Dao que creamos para la @Entity de Cliente.
	// @SupressWarnings, nos permite suprimir los warnings que nos genere, esto lo colocamos porque Spring da problemas.
	// @Transactional: Nos permite envolver el contenido del return de un método en una transacción, como un esquema ya sea de lectura o escritura.
	// por lo cual indicandole readOnly=true, le estamos señalando que la transacción es de solo lectura.
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		// llamar al método createQuery, para crear una query y mediante ResultList(), nos inserta el resultado
		// de la query en una lista.
		// Como vemos, en el Return tenemos que colocar algo que retorne tipo Cliente, ya que así lo definimos en la interface Dao
		// por lo cual, al usar el EntityManager, 
		return em.createQuery("from Cliente").getResultList();
	}

}
