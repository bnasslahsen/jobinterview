package com.badrconsulting.jobinterview.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the QR entity.
 */
public class QRDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	@Size(max = 10000)
	private String question;

	@NotNull
	@Size(max = 10000)
	private String answer;

	private Long domainId;

	public QRDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QRDTO(String question, String answer, Long domainId) {
		super();
		this.question = question;
		this.answer = answer;
		this.domainId = domainId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		QRDTO qRDTO = (QRDTO) o;
		if (qRDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), qRDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "QRDTO{" + "id=" + getId() + ", question='" + getQuestion() + "'"
				+ ", answer='" + getAnswer() + "'" + "}";
	}

}
