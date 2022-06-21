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
public class PositionType { // 工作类型
    private String fieldName; // 领域名
    private List<Direction> directions; // 方向
}
