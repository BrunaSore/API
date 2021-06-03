package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.org.serratec.backend.enums.Parentesco;
import br.org.serratec.backend.exception.DependenteException;

@Entity
public class Dependente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dependente")
	private Long id;
	@Enumerated(EnumType.STRING)
	private Parentesco parentesco;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_fucionario")
	private Funcionario funcionario;

	public Dependente() {
	
	}
	
	public Dependente(Parentesco parentesco) {
		super();
		this.parentesco = parentesco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}
	
	@Override
	public void setDataNascimento(LocalDate dataNascimento) throws DependenteException {
		LocalDate dataHoje = LocalDate.now();
		if (Period.between(dataNascimento, dataHoje).getYears() >= 18) {
			throw new DependenteException("Falha ao cadastrar! Dependente é maior de 18 anos");
		}
		super.setDataNascimento(dataNascimento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}