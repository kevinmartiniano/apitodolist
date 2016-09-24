package br.senai.sp.informatica.todolist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.ManagedTransactionAdapter;

import br.senai.sp.informatica.todolist.modelo.Lista;
import br.senai.sp.informatica.todolist.modelo.itemLista;

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
	
	@Transactional
	public void excluirItem(Long idItem) {
		itemLista item = manager.find(itemLista.class, idItem);
		Lista lista = item.getLista();
		lista.getItens().remove(item);
		manager.merge(lista);
	}
	
	public List<Lista> listarPorId(Long idLista) {
		TypedQuery<Lista> query =
				manager.createQuery("SELECT l FROM Lista l WHERE l.id = :idLista", Lista.class).setParameter("idLista", idLista);
		return query.getResultList();
	}
}
