package com.ucstu.guangbt.djzhaopin.model.account;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RegisterAccountRequest {
    @Pattern(regexp = "^1[3456789]\\d{9}$")
    private String userName;

    @Size(min = 4, max = 4)
    private String verificationCode;

    // {1:用户,2:HR}
    @Range(min = 1, max = 2)
    private Integer accountType;

    @Size(min = 6, max = 20)
    private String password;
}
