package br.senai.sp.informatica.todolist.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ItemDao {
	@PersistenceContext
	private EntityManager manager;
}
