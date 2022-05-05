package com.ucstu.guangbt.djzhaopin.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult<T> {

    private long totalCount;

    @JsonIgnore
    private List<T> contents;

    @JsonIgnore
    private String contentsName;

    @JsonAnyGetter
    public Map<String, Object> getContents() {
        return Collections.singletonMap(contentsName, contents);
    }
}
