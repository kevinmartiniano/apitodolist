package br.senai.sp.informatica.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.informatica.todolist.modelo.Lista;
import br.senai.sp.informatica.todolist.modelo.itemLista;

@Repository
public class ItemDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void marcarFeito(Long idItem, boolean valor) {
		itemLista item = manager.find(itemLista.class, idItem);
		item.setFeito(valor);
		manager.merge(item);
	}
	
	@Transactional
	public void inserir(Long idLista, itemLista item) {
		item.setLista(manager.find(Lista.class, idLista));
		manager.persist(item);
	}
	
	public List<itemLista> listarPorId(Long idItem) {
		TypedQuery<itemLista> query = manager.createQuery("SELECT i FROM itemLista WHERE i.id = " + idItem, itemLista.class);
		return query.getResultList();
	}
}
