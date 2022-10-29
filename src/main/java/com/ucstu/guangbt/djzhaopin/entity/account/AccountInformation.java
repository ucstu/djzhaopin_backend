package com.ucstu.guangbt.djzhaopin.entity.account;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

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
// 账号信息
public class AccountInformation {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID accountInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToOne(cascade = { CascadeType.ALL })
    private UserInformation userInformation;

    @JsonIgnore
    @JoinColumn(name = "hr_information_id")
    @OneToOne(cascade = { CascadeType.ALL })
    private HrInformation hrInformation;

    // {0:Admin,1:用户,2:HR}
    @NotNull
    @Range(min = 0, max = 2)
    private Integer accountType;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AccountAuthority> authorities;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AccountGroup> groups;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Boolean expired = false;

    @JsonIgnore
    private Boolean locked = false;

    @JsonIgnore
    private Boolean enabled = true;

    @JsonGetter("fullInformationId")
    public UUID getFullInformationId() {
        if (accountType == 1) {
            return userInformation != null ? userInformation.getUserInformationId() : null;
        } else if (accountType == 2) {
            return hrInformation != null ? hrInformation.getHrInformationId() : null;
        }
        return null;
    }

}
