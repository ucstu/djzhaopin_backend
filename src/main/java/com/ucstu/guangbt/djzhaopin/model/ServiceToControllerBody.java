package com.ucstu.guangbt.djzhaopin.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ServiceToControllerBody<T> {
    private List<ContentError> errors;
    private T content;

    public boolean isSuccess() {
        return errors == null || errors.isEmpty();
    }

    public ServiceToControllerBody<T> success(T content) {
        this.content = content;
        return this;
    }

    public ServiceToControllerBody<T> error(String field, String defaultMessage, Object rejectedValue) {
        errors.add(new ContentError(field, defaultMessage, rejectedValue));
        return this;
    }
}
