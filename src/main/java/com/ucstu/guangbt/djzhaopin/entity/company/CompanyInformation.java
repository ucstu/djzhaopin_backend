package com.ucstu.guangbt.djzhaopin.entity.company;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucstu.guangbt.djzhaopin.entity.Place;
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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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
public class CompanyInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID companyInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    private String hrInformationId;

    private String logoUrl;

    @NotNull
    private String companyName;

    private Integer recruitmentPosition;

    private String cityName;

    @Range(min = 1, max = 8)
    private Integer financingStage;

    @Range(min = 1, max = 6)
    private Integer scale;

    private String comprehensionName;

    private String address;

    private String about;

    private String fullName;

    private String legalRepresentative;

    private String registeredCapital;

    private String organizationType;

    private String establishmentTime;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> benefits;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    private List<PositionInformation> positionInformations;

    @JsonProperty("location")
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Place place;
}
