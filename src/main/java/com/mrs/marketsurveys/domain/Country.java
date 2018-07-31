package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country")
public @Getter @Setter @NoArgsConstructor class Country {
	@Id
	@Column(name = "code", nullable = false, updatable = false)
	private String code;

	@JsonIgnore
	@ManyToMany(mappedBy = "countries", fetch = FetchType.LAZY)
	private Set<MarketSurvey> surveys = new HashSet<>();

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof SubjectClassification)) {
			return false;
		}

		Country otherCountry = (Country) other;
		// two uninstanciated objects are never the same
		if (code == null
		        && otherCountry.getCode() == null) {
			return false;
		}

		return Objects.equals(code,
		        otherCountry.getCode());
	}

	@Override
	public int hashCode() {
		if (code != null) {
			return code.hashCode();
		} else {
			return super.hashCode();
		}
	}
}
