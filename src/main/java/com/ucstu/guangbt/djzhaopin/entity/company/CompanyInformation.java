package com.ucstu.guangbt.djzhaopin.entity.company;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CompanyInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID companyInformationId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private String hrId;

    @NotNull
    private String logo;
    @NotNull
    private String name;

    @Range(min = 1, max = 15000)
    private Integer recruitmentPosition;

    private String city;

    @NotNull
    @Range(min = 1, max = 7)
    private Integer financingStage;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer scale;

    private String comprehension;

    private String address;

    private String about;

    private String fullName;

    private String legalRepresentative;

    private String registeredCapital;

    private String organizationType;

    private String establishmentTime;

    @ElementCollection(targetClass = String.class)
    private List<String> companyBenefits;

    @OneToMany(cascade = { CascadeType.ALL })
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "positionInformationId")
    private List<PositionInformation> positionInformations;
}
