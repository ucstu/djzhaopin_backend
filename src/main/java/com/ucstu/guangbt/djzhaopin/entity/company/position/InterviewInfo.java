package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
// 职位面试信息
public class InterviewInfo {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID interviewInfoId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @NotNull
    @Range(min = 1, max = 3)
    private Integer situation;

    @NotNull
    @Range(min = 1, max = 4) // 1:面试通过 2:面试不通过 3:面试未安排 4:面试未知
    private Integer wheel;

    @NotNull
    @Range(min = 1, max = 2) // 1: 同意 2: 拒绝
    private Integer time;

    @NotNull
    @Range(min = 1, max = 4) // 1: 已面试 2: 未面试 3: 已录用 4: 未录用
    private Integer illustrate;

}
