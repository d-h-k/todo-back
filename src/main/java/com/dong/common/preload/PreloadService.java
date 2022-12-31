package com.dong.common.preload;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Component
public interface PreloadService<T> {
    PreloadHandler initPreload();
    List<String[]> readPreload(PreloadHandler handler);
    void savePreload(JpaRepository<T, Long> jpaRepository , PreloadHandler preloadHandler,  Class<T> saveType);
    List<String[]> headerPreloadInfo(PreloadHandler handler);
    void writeAfter(List<String[]> writeData);
}
