package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private Integer jobStatus;

    private String personalAdvantage;

    private String socialHomepage;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> pictureWorks;

    // {1:实名,2:匿名}
    private Integer privacySettings;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<JobExpectation> jobExpectations;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<EducationExperience> educationExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<WorkExperience> workExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<ProjectExperience> projectExperiences;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<DeliveryRecord> deliveryRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<AttentionRecord> attentionRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<InspectionRecord> inspectionRecords;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private Set<GarnerRecord> garnerRecords;
}
