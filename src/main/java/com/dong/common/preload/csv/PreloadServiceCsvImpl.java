package com.dong.common.preload.csv;

import com.dong.common.preload.PreloadHandler;
import com.dong.common.preload.PreloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@RequiredArgsConstructor
public class PreloadServiceCsvImpl implements PreloadService {

    @Value("${preload.filename}")
    String preloadFilename;
    @Value("${preload.path}")
    String preloadPath;

    private final ResourceLoader resourceLoader;

    @Override
    public PreloadHandler initPreload() {
        Resource resource =
    }

    @Override
    public List<String[]> readPreload(PreloadHandler handler) {
        return null;
    }

    @Override
    public List<String[]> headerPreloadInfo(PreloadHandler handler) {
        return null;
    }

    @Override
    public void writeAfter(List<String[]> writeData) {

    }
}
