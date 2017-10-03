package br.com.wplex.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Company Entity Model.
 *
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Entity
@Table(name = "wp_company")
public class Company implements Serializable {

	private static final long serialVersionUID = 5657929121638852757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	@NotEmpty(message = "Nome deve ser informado.")
	@Size(min = 1, max = 100)
	private String name;

	@NotEmpty(message = "Sigla deve ser informada.")
	@Size(min = 1, max = 5)
	private String initials;

	public Company() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.name;
	}

}
