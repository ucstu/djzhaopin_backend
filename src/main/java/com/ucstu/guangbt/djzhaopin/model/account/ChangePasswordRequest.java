package com.ucstu.guangbt.djzhaopin.model.account;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChangePasswordRequest {

    private String password;

    @Size(min = 4, max = 4)
    private String verificationCode;

}
