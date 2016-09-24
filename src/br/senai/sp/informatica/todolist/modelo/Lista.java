package br.senai.sp.informatica.todolist.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Lista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100)
	private String titulo;
	@OneToMany(mappedBy="lista", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<itemLista> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<itemLista> getItens() {
		return itens;
	}

	public void setItens(List<itemLista> itens) {
		this.itens = itens;
	}

	@JsonProperty("feito")
	public boolean isRealizada() {
		for (itemLista item : itens) {
			if (!item.isFeito()) {
				return false;
			}
		}
		return true;
	}
}
