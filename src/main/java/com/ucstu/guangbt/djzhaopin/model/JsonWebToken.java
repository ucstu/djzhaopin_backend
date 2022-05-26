package com.ucstu.guangbt.djzhaopin.model;

import java.io.Serializable;
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
public class JsonWebToken implements Serializable {

    private static final long serialVersionUID = -859098590985909859L;

    private UUID accountInformationId;

    private UUID fullInformationId;

    private UUID companyInformationId;

    private Integer accountType;

    private List<String> authorities;

    private Boolean expired;

    private Boolean locked;

    private Boolean enabled;

}
