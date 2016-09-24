package br.senai.sp.informatica.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.informatica.todolist.modelo.Lista;

@Repository
public class ListaDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir(Lista lista){
		manager.persist(lista);
		
	}
	
	public List<Lista> listar() {
		TypedQuery<Lista> query = 
				manager.createQuery("SELECT l FROM Lista l", Lista.class);
		return query.getResultList();
	}
	
	@Transactional
	public void excluir(Long idLista) {
		Lista lista = manager.find(Lista.class, idLista);
		manager.remove(lista);
	}
}
