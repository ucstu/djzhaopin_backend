package com.ucstu.guangbt.djzhaopin.entity.company;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
@EntityListeners(AuditingEntityListener.class) // 实体监听器
// 职位信息
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

    private String logoUrl; // 公司logo

    @NotNull
    private String companyName; // 公司名称

    private String cityName; // 城市名称

    @NotNull
    @Range(min = 1, max = 8)
    private Integer financingStage; // 融资阶段

    @NotNull
    @Range(min = 1, max = 6)
    private Integer scale; // 规模

    private String comprehensionName; // 语言名称

    @NotBlank
    private String address; // 地址

    @Type(type = "text")
    private String about; // 公司简介

    private String fullName; // 公司全称

    private String legalRepresentative; // 法定代表人

    private String registeredCapital; // 注册资金

    private String organizationType; // 组织形式

    private String establishmentTime; // 成立时间

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> benefits; // 公司福利

    @JsonIgnore
    @Range(min = 0, max = 180)
    private Float longitude; // 经度

    @JsonIgnore
    @Range(min = 0, max = 90)
    private Float latitude; // 纬度

    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<PositionInformation> positionInformations; // 职位信息

    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<AttentionRecord> attentionRecords; // 关注记录

    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<HrInformation> hrInformations; // HR信息

    @JsonGetter("location")
    public Map<String, Float> getLocation() {
        return new HashMap<String, Float>() {
            {
                put("longitude", longitude);
                put("latitude", latitude);
            }
        };
    }

    @JsonSetter("location")
    public void setLocation(@NotNull Map<String, Float> location) {
        this.longitude = location.get("longitude");
        this.latitude = location.get("latitude");
    }

    @JsonGetter("recruitmentPosition")
    public Integer getRecruitmentPosition() {
        return positionInformations != null ? positionInformations.size() : 0;
    }

}
