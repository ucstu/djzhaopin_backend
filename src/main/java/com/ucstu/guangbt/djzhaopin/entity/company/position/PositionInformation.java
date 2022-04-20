package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private String name;
    private Integer workingYears;
    private Integer education;

    @JsonProperty("directionTags")
    @OneToMany(cascade = { CascadeType.ALL })
    private List<PositionDirectionTag> positionDirectionTags;

    private Integer startingSalary;
    private Integer ceilingSalary;
    private String workArea;
    private Date releaseDate;
    private UUID companyId;
    private UUID hrId;
    private Integer positionType;
    private String department;

    @JsonProperty("highlights")
    @OneToMany(cascade = { CascadeType.ALL })
    private List<PositionHighlight> positionHighlights;

    private String description;
    private Integer weekendReleaseTime;
    private Date workTime;

    @JsonProperty("interviewInfo")
    @OneToOne(cascade = { CascadeType.ALL })
    private PositionInterviewInfo positionInterviewInfo;

    @JsonProperty("workingPlace")
    @OneToOne(cascade = { CascadeType.ALL })
    private PositionWorkingPlace positionWorkingPlace;

}
