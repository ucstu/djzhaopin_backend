package com.ucstu.guangbt.djzhaopin.entity.account;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class AccountInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
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

    // {1:用户,2:HR}
    @NotNull
    @Range(min = 1, max = 2)
    private Integer accountType;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Set<AccountAuthority> authorities;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
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
