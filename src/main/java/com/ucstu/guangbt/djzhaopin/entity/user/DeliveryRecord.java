package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DeliveryRecord {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID deliveryRecordId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @NotBlank

    private String userId;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer state;

    @NotBlank
    private String interviewTime;

    @NotBlank
    private String jobInformationId;
}
