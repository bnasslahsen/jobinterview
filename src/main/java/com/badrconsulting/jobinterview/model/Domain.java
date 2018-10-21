package com.badrconsulting.jobinterview.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Domain.
 */
@Entity
@Table(name = "domain")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Domain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "language", nullable = false)
	private String language;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Domain name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public Domain language(String language) {
		this.language = language;
		return this;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters
	// here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Domain domain = (Domain) o;
		if (domain.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), domain.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Domain{" + "id=" + getId() + ", name='" + getName() + "'" + ", language='"
				+ getLanguage() + "'" + "}";
	}

}
