package br.com.wplex.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Garage Entity Model.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@Entity
@Table(name = "wp_garage")
public class Garage implements Serializable {

	private static final long serialVersionUID = -932522159790292048L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	@NotEmpty(message = "Nome deve ser informado.")
	@Size(max = 100)
	private String name;

	@NotEmpty(message = "Sigla deve ser informada.")
	@Size(max = 5)
	private String initials;

	@ManyToOne
	private Company company;

	public Garage() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Garage [Id=" + Id + ", created=" + created + ", name=" + name + ", initials=" + initials + ", company="
				+ company + "]";
	}

}
