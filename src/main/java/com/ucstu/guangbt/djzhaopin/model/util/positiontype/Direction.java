package com.ucstu.guangbt.djzhaopin.model.util.positiontype;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Direction {
    private String directionName;
    private List<String> positions;
}
