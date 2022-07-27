package com.dong.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListWrapper<T extends Page<?>> {

    private HttpStatus status;
    private T contents;
    private String message;
    private Sort sort;

    @SuppressWarnings("unchecked : type casting is safe")
    public static <T extends Page<?>> ResponseListWrapper<T> listWrapOk(T contents) {
        return (ResponseListWrapper<T>) ResponseListWrapper.builder()
                .contents(contents)
                .sort(contents.getSort())
                .status(HttpStatus.OK)
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    public ResponseEntity<?> jsonResponse() {
        return ResponseEntity
                .status(this.status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(contents);
    }
}