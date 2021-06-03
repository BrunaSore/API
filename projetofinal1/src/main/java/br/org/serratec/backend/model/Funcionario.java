package br.org.serratec.backend.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Funcionario extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	private Long id;
	@DecimalMin(value = "1", message = "O valor mínimo é R${value}.00")
	private BigDecimal salarioBruto;
	private BigDecimal descontoInss;
	private BigDecimal descontoIr;
	private BigDecimal salarioLiquido;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.EAGER)
	List<Dependente> dependentes;
	
	public Funcionario() {
	
	}
	
	public Funcionario(Long id, BigDecimal salarioBruto, BigDecimal descontoInss, BigDecimal descontoIr, BigDecimal salarioLiquido) {
		super();
		this.id = id;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIr = descontoIr;
		this.salarioLiquido = salarioLiquido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public BigDecimal getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(BigDecimal descontoInss) {
		this.descontoInss = descontoInss;
	}

	public BigDecimal getDescontoIr() {
		return descontoIr;
	}

	public void setDescontoIr(BigDecimal descontoIr) {
		this.descontoIr = descontoIr;
	}

	public BigDecimal getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(BigDecimal salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	
	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}