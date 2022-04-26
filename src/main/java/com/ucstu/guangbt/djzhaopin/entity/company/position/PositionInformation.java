package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class PositionInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID positionInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @NotBlank
    private String name;
    @Range(min = 0, max = 5)
    private Integer workingYears;
    @Range(min = 0, max = 4)
    private Integer education;

    @ElementCollection(targetClass = String.class)
    private List<String> directionTags;

    @NotNull
    private Integer startingSalary;
    @NotNull
    private Integer ceilingSalary;

    private String workArea;
    private Date releaseDate;

    @NotNull
    @Type(type = "uuid-char")
    private UUID companyInformationId;

    @NotNull
    @Type(type = "uuid-char")
    private UUID hrInformationId;

    @Range(min = 1, max = 3)
    private Integer positionType;
    private String department;

    @ElementCollection(targetClass = String.class)
    private List<String> highlights;

    @NotBlank
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
