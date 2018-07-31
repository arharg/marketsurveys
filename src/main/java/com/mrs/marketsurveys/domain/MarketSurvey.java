package com.mrs.marketsurveys.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrs.marketsurveys.utils.IdGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@NamedEntityGraph(
        name = "previewMarketSurvey",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("description"),
                @NamedAttributeNode("subjects"),
                @NamedAttributeNode("conditions")
        })
@Entity
@Table(name = "market_survey")
public @Getter @Setter @NoArgsConstructor class MarketSurvey {
	@Id
	@Column(name = "survey_id", nullable = false, updatable = false)
	private String id = IdGenerator.createId();

	private String description;

	@Column(name = "time_for_fieldwork", nullable = true)
	private long timeForFieldwork;

	@Column(name = "target_desc", nullable = true)
	private String targetGroupDescription;

	@Column(name = "sample_size", nullable = true)
	private int sampleSize;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private SurveyCollectionChannel channel;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private SurveyOrganisation organisation;

	@Enumerated(EnumType.STRING)
	@Column(name = "registration_type", nullable = true)
	private SurveyRegistrationType registrationType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private SurveyMethod method;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private SurveyType type;

	@Column(name = "is_time_series")
	private boolean timeSeries;

	@ManyToMany()
	@JoinTable(
	        name = "survey_country",
	        joinColumns = @JoinColumn(name = "survey_id"),
	        inverseJoinColumns = @JoinColumn(name = "country_code"))
	private Set<Country> countries = new HashSet<>();

	@ManyToMany()
	@JoinTable(
	        name = "survey_subject",
	        joinColumns = @JoinColumn(name = "survey_id"),
	        inverseJoinColumns = @JoinColumn(name = "classification_value"))
	private Set<SubjectClassification> subjects = new HashSet<>();

	@ManyToMany()
	@JoinTable(
	        name = "survey_conditions",
	        joinColumns = @JoinColumn(name = "party_id"),
	        inverseJoinColumns = @JoinColumn(name = "condition_id"))
	private Set<Condition> conditions = new HashSet<>();

//	@JoinColumn(name = "conducted_by", referencedColumnName = "party_id", insertable = false, updatable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Party conductedBy;

	@ManyToMany()
	@JoinTable(
	        name = "survey_owner",
	        joinColumns = @JoinColumn(name = "survey_id"),
	        inverseJoinColumns = @JoinColumn(name = "party_id"))
	private Set<Party> owners = new HashSet<>();

	@JsonProperty("targetGroupDescription")
	private void unpackNested(JsonNode target) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		this.targetGroupDescription = mapper.writeValueAsString(target);
	}

//	public void addSubject(SubjectClassification subject) {
//		this.subjects.add(subject);
//		subject.getSurveys().add(this);
//	}
//
//	public void removeSubject(SubjectClassification subject) {
//		this.subjects.remove(subject);
//		subject.getSurveys().remove(this);
//	}
//
//	public void addOwner(Party owner) {
//		this.owners.add(owner);
//		owner.getSurveys().add(this);
//	}
//
//	public void removeOwner(Party owner) {
//		this.owners.remove(owner);
//		owner.getSurveys().remove(this);
//	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof MarketSurvey)) {
			return false;
		}

		MarketSurvey otherSurvey = (MarketSurvey) other;
		// two uninstanciated objects are never the same
		if (id == null && otherSurvey.getId() == null) {
			return false;
		}

		return Objects.equals(id, otherSurvey.getId());
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
