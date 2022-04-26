package com.ucstu.guangbt.djzhaopin.model.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CityInformation {
    private String provinceName;
    private List<String> cities;
}
