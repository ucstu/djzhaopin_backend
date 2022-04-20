package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
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
public class UserInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID userId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
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
}
