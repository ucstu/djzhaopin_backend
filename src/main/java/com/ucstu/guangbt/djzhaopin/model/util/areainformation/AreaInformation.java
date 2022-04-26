package com.ucstu.guangbt.djzhaopin.model.util.areainformation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AreaInformation {
    private List<String> areas;
    private String countyName;
}
