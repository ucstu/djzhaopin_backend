package com.ucstu.guangbt.djzhaopin.model.account;

import jakarta.validation.constraints.Email;
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
    @Email
    private String userName;

    @Size(min = 6, max = 20)
    private String password;
}
