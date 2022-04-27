package com.ucstu.guangbt.djzhaopin.model;

import java.net.URI;
import java.sql.Date;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseBody<T> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date(System.currentTimeMillis());
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ContentError> errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class BodyBuilder {
        private Date timestamp = new Date(System.currentTimeMillis());
        private int status;
        private HttpHeaders headers = new HttpHeaders();

        public BodyBuilder header(String headerName, String... headerValues) {
            for (String headerValue : headerValues) {
                this.headers.add(headerName, headerValue);
            }
            return this;
        }

        public BodyBuilder headers(@Nullable HttpHeaders headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        public BodyBuilder headers(Consumer<HttpHeaders> headersConsumer) {
            headersConsumer.accept(this.headers);
            return this;
        }

        public BodyBuilder allow(HttpMethod... allowedMethods) {
            this.headers.setAllow(new HashSet<>(Arrays.asList(allowedMethods)));
            return this;
        }

        public BodyBuilder contentLength(long contentLength) {
            this.headers.setContentLength(contentLength);
            return this;
        }

        public BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        public BodyBuilder eTag(String etag) {
            if (!etag.startsWith("\"") && !etag.startsWith("W/\"")) {
                etag = "\"" + etag;
            }
            if (!etag.endsWith("\"")) {
                etag = etag + "\"";
            }
            this.headers.setETag(etag);
            return this;
        }

        public BodyBuilder lastModified(ZonedDateTime date) {
            this.headers.setLastModified(date);
            return this;
        }

        public BodyBuilder lastModified(Instant date) {
            this.headers.setLastModified(date);
            return this;
        }

        public BodyBuilder lastModified(long date) {
            this.headers.setLastModified(date);
            return this;
        }

        public BodyBuilder location(URI location) {
            this.headers.setLocation(location);
            return this;
        }

        public BodyBuilder cacheControl(CacheControl cacheControl) {
            this.headers.setCacheControl(cacheControl);
            return this;
        }

        public BodyBuilder varyBy(String... requestHeaders) {
            this.headers.setVary(Arrays.asList(requestHeaders));
            return this;
        }

        public <T> ResponseEntity<ResponseBody<T>> build() {
            return body(null);
        }

        public <T> ResponseEntity<ResponseBody<T>> body(@Nullable T body) {
            return new ResponseEntity<>(
                    new ResponseBody<T>(this.timestamp, this.status, HttpStatus.valueOf(this.status).getReasonPhrase(),
                            null, body),
                    this.headers, this.status);
        }

        public <T> ResponseEntity<ResponseBody<T>> errors(@Nullable List<ContentError> errors) {
            return new ResponseEntity<>(
                    new ResponseBody<T>(this.timestamp, this.status, HttpStatus.valueOf(this.status)
                            .getReasonPhrase(), errors, null),
                    this.headers, this.status);
        }
    }

    public static BodyBuilder status(HttpStatus status) {
        return new BodyBuilder().setStatus(status.value());
    }

    public static BodyBuilder status(int rawStatus) {
        return new BodyBuilder().setStatus(rawStatus);
    }

    public static BodyBuilder success() {
        return status(HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseBody<T>> success(@Nullable T data) {
        return status(HttpStatus.OK).body(data);
    }

    public static BodyBuilder created() {
        return status(HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ResponseBody<T>> created(@Nullable T data) {
        return status(HttpStatus.CREATED).body(data);
    }

    public static BodyBuilder accepted() {
        return status(HttpStatus.ACCEPTED);
    }

    public static <T> ResponseEntity<ResponseBody<T>> accepted(@Nullable T data) {
        return status(HttpStatus.ACCEPTED).body(data);
    }

    public static BodyBuilder noContent() {
        return status(HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<ResponseBody<T>> noContent(@Nullable T data) {
        return status(HttpStatus.NO_CONTENT).body(data);
    }

    public static BodyBuilder badRequest() {
        return status(HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ResponseBody<T>> badRequest(@Nullable List<ContentError> errors) {
        return status(HttpStatus.BAD_REQUEST).errors(errors);
    }

    public static BodyBuilder unauthorized() {
        return status(HttpStatus.UNAUTHORIZED);
    }

    public static <T> ResponseEntity<ResponseBody<T>> unauthorized(@Nullable List<ContentError> errors) {
        return status(HttpStatus.UNAUTHORIZED).errors(errors);
    }

    public static BodyBuilder forbidden() {
        return status(HttpStatus.FORBIDDEN);
    }

    public static <T> ResponseEntity<ResponseBody<T>> forbidden(@Nullable List<ContentError> errors) {
        return status(HttpStatus.FORBIDDEN).errors(errors);
    }

    public static BodyBuilder notFound() {
        return status(HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<ResponseBody<T>> notFound(@Nullable List<ContentError> errors) {
        return status(HttpStatus.NOT_FOUND).errors(errors);
    }

    public static BodyBuilder conflict() {
        return status(HttpStatus.CONFLICT);
    }

    public static <T> ResponseEntity<ResponseBody<T>> conflict(@Nullable List<ContentError> errors) {
        return status(HttpStatus.CONFLICT).errors(errors);
    }

    public static BodyBuilder internalServerError() {
        return status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<ResponseBody<T>> internalServerError(@Nullable List<ContentError> errors) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR).errors(errors);
    }

    public static <T> ResponseEntity<ResponseBody<T>> handle(ServiceToControllerBody<T> serviceToControllerBody) {
        if (!serviceToControllerBody.isSuccess()) {
            return badRequest(serviceToControllerBody.getErrors());
        }
        return success(serviceToControllerBody.getContent());
    }
}
