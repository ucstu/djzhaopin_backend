package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;

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
// User 关注记录
public class AttentionRecord {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID attentionRecordId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    private UserInformation userInformation;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    private CompanyInformation companyInformation;

    @JsonGetter("userInformationId")
    public UUID getUserInformationId() {
        return userInformation != null ? userInformation.getUserInformationId() : null;
    }

    @JsonSetter("userInformationId")
    public void setUserInformationId(UUID userInformationId) {
        userInformation = new UserInformation().setUserInformationId(userInformationId);
    }

    @JsonGetter("companyInformationId")
    public UUID getCompanyInformationId() {
        return companyInformation != null ? companyInformation.getCompanyInformationId() : null;
    }

    @JsonSetter("companyInformationId")
    public void setCompanyInformationId(UUID companyInformationId) {
        companyInformation = new CompanyInformation().setCompanyInformationId(companyInformationId);
    }

}
