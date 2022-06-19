package com.ucstu.guangbt.djzhaopin.model.account;

import jakarta.validation.constraints.NotBlank;
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
    private String userName;

    private String password;

}
