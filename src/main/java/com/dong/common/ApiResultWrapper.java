package com.dong.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Builder
@AllArgsConstructor
public class ApiResultWrapper<T> {

    private HttpStatus httpStatus;
    private T data;

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ApiResultWrapper<T> wrapOk(T data) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ApiResultWrapper<T> wrapCreated(T data) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @SuppressWarnings("unchecked : Server error returns only 500 code according to security policy")
    public static <T> ApiResultWrapper<T> wrapFail(String message) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(message)
                .build();
    }

    public ResponseEntity<?> jsonResponse() {
        return ResponseEntity
                .status(this.httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data);
    }

    public ResponseEntity<?> response() {
        return ResponseEntity
                .status(this.httpStatus)
                .body(data);
    }


}
