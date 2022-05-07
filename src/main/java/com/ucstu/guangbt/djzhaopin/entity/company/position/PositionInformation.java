package com.ucstu.guangbt.djzhaopin.entity.company.position;

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
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class PositionInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
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
    private Set<String> directionTags;

    @NotNull
    private Integer startingSalary;

    @NotNull
    private Integer ceilingSalary;

    private String workProvinceName;

    private String workCityName;

    private String workAreaName;

    @NotNull
    @Range(min = 1, max = 3)
    private Integer workType;

    private String departmentName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> highlights;

    @NotBlank
    private String description;

    @Range(min = 1, max = 3)
    private Integer weekendReleaseTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date workTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date overTime;

    @JsonIgnore
    @Range(min = 0, max = 180)
    private Float longitude;

    @JsonIgnore
    @Range(min = 0, max = 90)
    private Float latitude;

    @NotNull
    @JoinColumn
    @JsonProperty("interviewInfo")
    @OneToOne(cascade = { CascadeType.ALL })
    private InterviewInfo interviewInfo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_information_id")
    private CompanyInformation companyInformation;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "hr_information_id")
    private HrInformation hrInformation;

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<DeliveryRecord> deliveryRecords;

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<GarnerRecord> garnerRecords;

    @JsonIgnore
    @JoinColumn(name = "position_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<UserInspectionRecord> userInspectionRecords;

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
