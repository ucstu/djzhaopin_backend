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
public class Direction { // 细分标签
    private String directionName; // 方向名称
    private List<String> positions; // 职位
}
