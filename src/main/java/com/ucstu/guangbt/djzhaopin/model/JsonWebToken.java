package com.ucstu.guangbt.djzhaopin.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JsonWebToken {

    private UUID accountInformationId;

    private UUID companyInformationId;

    private UUID fullInformationId;

    private Integer accountType;

    private List<String> authorities;

    private Boolean expired;

    private Boolean locked;

    private Boolean enabled;

}
