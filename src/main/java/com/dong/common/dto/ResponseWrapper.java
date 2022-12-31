package com.dong.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Builder
@AllArgsConstructor
public class ResponseWrapper<T> {

    private final HttpStatus status;
    private final T contents;
    private final String message;

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ResponseWrapper<T> wrapOk(T contents) {
        return (ResponseWrapper<T>) ResponseWrapper.builder()
                .contents(contents)
                .status(HttpStatus.OK)
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T> ResponseWrapper<T> wrapCreated(T contents) {
        return (ResponseWrapper<T>) ResponseWrapper.builder()
                .contents(contents)
                .status(HttpStatus.CREATED)
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    @SuppressWarnings("unchecked : Server error returns only 500 code according to security policy")
    public static <T> ResponseWrapper<T> wrapFail(String message) {
        return (ResponseWrapper<T>) ResponseWrapper.builder()
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
