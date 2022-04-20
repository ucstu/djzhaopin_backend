package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PositionInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID positionInformationId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
    String name;
    Integer workingYears;

    Integer education;
    @JsonProperty("directionTags")
    List<PositionDirectionTag> positionDirectionTags;
    Integer startingSalary;
    Integer ceilingSalary;
    String workArea;
    Date releaseDate;
    UUID companyId;
    UUID hrId;
    Integer positionType;
    String department;
    @JsonProperty("highlights")
    List<PositionHighlight> positionHighlights;
    String description;
    Integer weekendReleaseTime;
    Date workTime;
    @JsonProperty("interviewInfo")
    PositionInterviewInfo positionInterviewInfo;
    @JsonProperty("workingPlace")
    PositionWorkingPlace positionWorkingPlace;

}
