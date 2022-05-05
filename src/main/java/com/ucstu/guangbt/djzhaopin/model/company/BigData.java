package com.ucstu.guangbt.djzhaopin.model.company;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BigData {
    private Date date;
    private Integer inspectionRecordCount;
    private Integer deliveryRecordCount;
    private Integer onlineCommunicateCount;
}
