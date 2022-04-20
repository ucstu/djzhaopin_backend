package com.ucstu.guangbt.djzhaopin.model.account;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAccountRequest {
    @NotBlank
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    private String userName;
    @NotBlank
    @Size(min = 4, max = 4)
    private String verificationCode;
    @NotNull
    // {1:用户,2:HR}
    @Range(min = 1, max = 2)
    private Integer accountType;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
