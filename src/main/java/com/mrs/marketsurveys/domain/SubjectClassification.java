package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "subject_classification")
public @Getter @Setter @NoArgsConstructor class SubjectClassification {
	@Id
	@Column(name = "classification_value", nullable = false, updatable = false)
	private Integer classificationValue;

	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "subjects")
	private Set<MarketSurvey> surveys = new HashSet<>();

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof SubjectClassification)) {
			return false;
		}

		SubjectClassification otherSubject = (SubjectClassification) other;
		// two uninstanciated objects are never the same
		if (classificationValue == null
		        && otherSubject.getClassificationValue() == null) {
			return false;
		}

		return Objects.equals(classificationValue,
		        otherSubject.getClassificationValue());
	}

	@Override
	public int hashCode() {
		if (classificationValue != null) {
			return classificationValue.hashCode();
		} else {
			return super.hashCode();
		}
	}
}
