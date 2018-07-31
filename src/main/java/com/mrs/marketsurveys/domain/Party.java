package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("party")
@Table(name = "party")
public @Getter @Setter @NoArgsConstructor class Party {
	@NotNull
	@Id
	@Column(
	        name = "party_id",
	        nullable = false,
	        updatable = false,
	        unique = true)
	private String id;

	@Column(unique = true)
	private String name;

	private String address;

	@ManyToOne
	@JoinColumn(name = "country")
	private Country country;

	@JsonIgnore
	@ManyToMany(mappedBy = "owners")
	private Set<MarketSurvey> surveys = new HashSet<>();

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof Party)) {
			return false;
		}

		Party otherParty = (Party) other;
		// two uninstanciated objects are never the same
		if (id == null && otherParty.getId() == null) {
			return false;
		}

		return Objects.equals(id, otherParty.getId());
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}
	}

}
