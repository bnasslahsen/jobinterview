package com.badrconsulting.jobinterview.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QR.
 */
@Entity
@Table(name = "qr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QR implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Lob
	@Column(name = "question", nullable = false)
	private String question;

	@NotNull
	@Lob
	@Column(name = "answer", nullable = false)
	private String answer;

	@ManyToOne
	private Domain domain;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public QR question(String question) {
		this.question = question;
		return this;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public QR answer(String answer) {
		this.answer = answer;
		return this;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Domain getDomain() {
		return domain;
	}

	public QR domain(Domain domain) {
		this.domain = domain;
		return this;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
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
		QR qR = (QR) o;
		if (qR.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), qR.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "QR{" + "id=" + getId() + ", question='" + getQuestion() + "'"
				+ ", answer='" + getAnswer() + "'" + "}";
	}

}
