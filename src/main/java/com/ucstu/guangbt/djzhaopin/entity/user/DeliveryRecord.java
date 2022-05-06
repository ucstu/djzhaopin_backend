package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class DeliveryRecord {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID deliveryRecordId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    // {1:待查看,2:已查看,3:通过筛选,4:约面试,5:不合适}
    @Range(min = 1, max = 5)
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date interviewTime;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    private UserInformation userInformation;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    private CompanyInformation companyInformation;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    private PositionInformation positionInformation;

    @JsonGetter("userInformationId")
    public UUID getUserInformationId() {
        return userInformation.getUserInformationId();
    }

    @JsonSetter("userInformationId")
    public void setUserInformationId(UUID userInformationId) {
        userInformation = new UserInformation().setUserInformationId(userInformationId);
    }

    @JsonGetter("companyInformationId")
    public UUID getCompanyInformationId() {
        return companyInformation.getCompanyInformationId();
    }

    @JsonSetter("companyInformationId")
    public void setCompanyInformationId(UUID companyInformationId) {
        companyInformation = new CompanyInformation().setCompanyInformationId(companyInformationId);
    }

    @JsonGetter("positionInformationId")
    public UUID getPositionInformationId() {
        return positionInformation.getPositionInformationId();
    }

    @JsonSetter("positionInformationId")
    public void setPositionInformationId(UUID positionInformationId) {
        positionInformation = new PositionInformation().setPositionInformationId(positionInformationId);
    }

}
