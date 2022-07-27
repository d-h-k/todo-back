package com.dong.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Builder
@AllArgsConstructor
public class ApiResultWrapper<T> {

    private HttpStatus status;
    private T contents;
    private String message;

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ApiResultWrapper<T> wrapOk(T contents) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .contents(contents)
                .status(HttpStatus.OK)
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ApiResultWrapper<T> wrapCreated(T contents) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .contents(contents)
                .status(HttpStatus.CREATED)
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    @SuppressWarnings("unchecked : Server error returns only 500 code according to security policy")
    public static <T> ApiResultWrapper<T> wrapFail(String message) {
        return (ApiResultWrapper<T>) ApiResultWrapper.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contents(null)
                .message(message)
                .build();
    }

    public ResponseEntity<?> jsonResponse() {
        return ResponseEntity
                .status(this.status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(contents);
    }

}
