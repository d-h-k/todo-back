package com.dong.common.preload;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Component
public interface PreloadService {
    PreloadHandler initPreload();
    List<String[]> readPreload(PreloadHandler handler);
    List<String[]> headerPreloadInfo(PreloadHandler handler);
    void writeAfter(List<String[]> writeData);
}
