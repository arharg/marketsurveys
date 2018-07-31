package com.mrs.marketsurveys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrs.marketsurveys.domain.RequestForInformation;

@Repository
public interface RequestForInformationRepository
        extends JpaRepository<RequestForInformation, String> {

}
