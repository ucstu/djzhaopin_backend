package com.ucstu.guangbt.djzhaopin.entity.account;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AccountInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID accountId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @JsonProperty("userInfoId")
    @OneToOne(cascade = { CascadeType.ALL })
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    private UserInformation userInformation;

    @JsonProperty("hrInfoId")
    @OneToOne(cascade = { CascadeType.ALL })
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "hrId")
    private HrInformation hrInformation;

    // {1:用户,2:HR}
    private Integer accountType;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    @NotFound(action = NotFoundAction.IGNORE)
    private List<AccountAuthority> authorities;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL })
    @NotFound(action = NotFoundAction.IGNORE)
    private List<AccountGroup> groups;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Boolean expired = false;

    @JsonIgnore
    private Boolean locked = false;

    @JsonIgnore
    private Boolean enabled = true;
}
