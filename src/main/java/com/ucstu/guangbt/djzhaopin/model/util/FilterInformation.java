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
public class FilterInformation {
    private List<String> occupationalBreakdown;
    private List<String> expectedSalary;
    private List<String> workExperience;
    private List<String> education;
    private List<String> natureWork;
    private List<String> companySize;
    private List<String> financingStage;
    private List<String> industryField;
}
