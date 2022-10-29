package com.ucstu.guangbt.djzhaopin.model.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RegisterAccountRequest {

    @NotBlank
    private String userName;

    @Size(min = 4, max = 4)
    private String verificationCode;

    // {1:用户,2:HR}
    @Range(min = 1, max = 2)
    private Integer accountType;

    private String password;

}
