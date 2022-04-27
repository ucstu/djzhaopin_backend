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
    private List<ErrorContent> errors;
    private boolean create;
    private T content;

    public boolean isSuccess() {
        return errors == null || errors.isEmpty();
    }

    public boolean isCreate() {
        return create;
    }

    public ServiceToControllerBody<T> success(T content) {
        this.content = content;
        this.create = false;
        return this;
    }

    public ServiceToControllerBody<T> created(T content) {
        this.content = content;
        this.create = true;
        return this;
    }

    public ServiceToControllerBody<T> error(String field, String defaultMessage, Object rejectedValue) {
        errors.add(new ErrorContent(field, defaultMessage, rejectedValue));
        return this;
    }
}
