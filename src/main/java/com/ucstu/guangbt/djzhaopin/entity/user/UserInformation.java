package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class UserInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID userInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date updatedAt;

    private String avatar;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String sex;

    private Integer age;

    private String city;

    private String phoneNumber;

    private String email;

    private Integer workingYears;

    // {1:大专,2:本科,3:硕士,4:博士}
    private Integer education;

    // {1:随时入职,2:2周内入职,3:1月内入职}
    private String jobStatus;

    private String personalAdvantage;

    private String socialHomepage;

    private String pictureWorks;

    // {1:实名,2:匿名}
    private Integer privacySettings;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<JobExpectation> jobExpectations;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<EducationExperience> educationExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<WorkExperience> workExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<ProjectExperience> projectExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<DeliveryRecord> deliveryRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<AttentionRecord> attentionRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<InspectionRecord> inspectionRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<GarnerRecord> garnerRecords;
}
