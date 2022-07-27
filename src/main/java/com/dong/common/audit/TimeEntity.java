package com.dong.common.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class TimeEntity {

    @JsonIgnore
    @Transient
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern = DATETIME_FORMAT)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    @DateTimeFormat(pattern = DATETIME_FORMAT)
    private LocalDateTime modifiedAt;
}
