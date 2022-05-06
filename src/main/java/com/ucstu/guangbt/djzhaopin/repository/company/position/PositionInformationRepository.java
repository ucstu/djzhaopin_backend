package com.ucstu.guangbt.djzhaopin.repository.company.position;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionInformationRepository
                extends JpaRepository<PositionInformation, UUID> {

}
