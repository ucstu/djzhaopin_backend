package com.ucstu.guangbt.djzhaopin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ErrorContent {

    private String field;

    private String defaultMessage;

    private Object rejectedValue;

}
