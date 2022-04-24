package com.ucstu.guangbt.djzhaopin.entity.hr;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class HrInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = Access.READ_ONLY)
    @JsonProperty(access = Access.READ_ONLY)
    private UUID hrInformationId;

    @CreatedDate
    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date updatedAt;

    @JsonProperty(value = "companyInformationId", access = Access.READ_ONLY)
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "companyInformationId")
    private CompanyInformation companyInformation;

    private String avatar;

    private String name;

    private String post;

    @Email
    private String acceptEmail;

    @Size(min = 11, max = 11)
    private String phoneNumber;
}
