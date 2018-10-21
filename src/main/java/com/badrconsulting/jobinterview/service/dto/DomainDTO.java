package com.badrconsulting.jobinterview.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Domain entity.
 */
public class DomainDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	private String name;

	@NotNull
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

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DomainDTO domainDTO = (DomainDTO) o;
		if (domainDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), domainDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "DomainDTO{" + "id=" + getId() + ", name='" + getName() + "'"
				+ ", language='" + getLanguage() + "'" + "}";
	}

}
