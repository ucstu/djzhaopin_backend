package com.ucstu.guangbt.djzhaopin.model.account;

import jakarta.validation.constraints.NotBlank;
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
public class LoginAccountRequest {
    @NotBlank
    @Pattern(regexp = "^1[3456789]\\d{9}$")
    private String userName;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
