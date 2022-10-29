package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
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
@EntityListeners(AuditingEntityListener.class)
// 职位信息
public class PositionInformation {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID positionInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @NotBlank
    private String positionName;

    @NotBlank
    private String positionType;

    @NotNull
    @Range(min = 1, max = 6)
    private Integer workingYears;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer education;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> directionTags; // 方向标签

    @NotNull
    private Integer startingSalary; // 起始薪资

    @NotNull
    private Integer ceilingSalary; // 最高薪资

    private String workProvinceName; // 工作地点省份名称

    private String workCityName; // 工作地点城市名称

    private String workAreaName; // 工作地点区域名称

    @NotNull
    @Range(min = 1, max = 3)
    private Integer workType; // 工作类型

    private String departmentName; // 部门名称

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> highlights; // 关键词

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description; // 职位描述

    @Range(min = 1, max = 3)
    private Integer weekendReleaseTime; // 周末休息情况

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date workTime; // 工作时间

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date overTime; // 加班时间

    @JsonIgnore
    @Range(min = 0, max = 180)
    private Float longitude; // 经度

    @JsonIgnore
    @Range(min = 0, max = 90)
    private Float latitude; // 纬度

    @Valid
    @NotNull
    @JoinColumn
    @JsonProperty("interviewInfo")
    @OneToOne(cascade = { CascadeType.ALL })
    private InterviewInfo interviewInfo; // 面试信息

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    private CompanyInformation companyInformation; // 公司信息

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hr_information_id")
    private HrInformation hrInformation; // HR信息

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<DeliveryRecord> deliveryRecords; // 投递记录

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<GarnerRecord> garnerRecords; // 收藏记录

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<UserInspectionRecord> userInspectionRecords; // 用户查看记录

    @JsonGetter("companyInformationId")
    public UUID getCompanyInformationId() {
        return companyInformation != null ? companyInformation.getCompanyInformationId() : null;
    }

    @JsonSetter("companyInformationId")
    public void setCompanyInformationId(UUID companyInformationId) {
        companyInformation = new CompanyInformation().setCompanyInformationId(companyInformationId);
    }

    @JsonGetter("hrInformationId")
    public UUID getHrInformationId() {
        return hrInformation != null ? hrInformation.getHrInformationId() : null;
    }

    @JsonSetter("hrInformationId")
    public void setHrInformationId(UUID hrInformationId) {
        hrInformation = new HrInformation().setHrInformationId(hrInformationId);
    }

    @JsonGetter("workingPlace")
    public Map<String, Float> getWorkingPlace() {
        return new HashMap<String, Float>() {
            {
                put("longitude", longitude);
                put("latitude", latitude);
            }
        };
    }

    @JsonSetter("workingPlace")
    public void setWorkingPlace(Map<String, Float> workingPlace) {
        longitude = workingPlace.get("longitude");
        latitude = workingPlace.get("latitude");
    }

}
