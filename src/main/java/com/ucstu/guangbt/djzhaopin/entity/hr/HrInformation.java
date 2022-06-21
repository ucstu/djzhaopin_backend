package com.ucstu.guangbt.djzhaopin.entity.hr;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class) // 实体监听器
// HR信息
public class HrInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID hrInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false) // 数据库不允许修改 且不允许为空
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false) // 数据库不允许修改 且不允许为空
    private Date updatedAt;

    private String avatarUrl; // 头像url

    private String hrName; // 姓名

    private String postName; // 职位名称

    @Email
    private String acceptEmail; // 邮箱

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    private CompanyInformation companyInformation;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hr_information_id")
    private List<PositionInformation> positionInformations;

    @JsonIgnore
    @JoinColumn(name = "hr_information_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HrInspectionRecord> hrInspectionRecords;

    @JsonGetter("companyInformationId")
    public UUID getCompanyInformationId() {
        return companyInformation != null ? companyInformation.getCompanyInformationId() : null;
    }

    @JsonSetter("companyInformationId")
    public void setCompanyInformationId(UUID companyInformationId) {
        companyInformation = new CompanyInformation().setCompanyInformationId(companyInformationId);
    }

}
