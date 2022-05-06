package com.ucstu.guangbt.djzhaopin.entity.hr;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.hibernate.annotations.Type;
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
public class HrInspectionRecord {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID hrInspectionRecordId;

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
    @JoinColumn(name = "hr_information_id")
    private HrInformation hrInformation;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    private UserInformation userInformation;

    @JsonGetter("hrInformationId")
    public UUID getHrInformationId() {
        return hrInformation.getHrInformationId();
    }

    @JsonSetter("hrInformationId")
    public void setHrInformationId(UUID hrInformationId) {
        hrInformation = new HrInformation().setHrInformationId(hrInformationId);
    }

    @JsonGetter("userInformationId")
    public UUID getUserInformationId() {
        return userInformation.getUserInformationId();
    }

    @JsonSetter("userInformationId")
    public void setUserInformationId(UUID userInformationId) {
        userInformation = new UserInformation().setUserInformationId(userInformationId);
    }

}
