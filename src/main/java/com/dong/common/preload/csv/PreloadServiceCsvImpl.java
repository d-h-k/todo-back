package com.dong.common.preload.csv;

import com.dong.common.preload.PreloadException;
import com.dong.common.preload.PreloadHandler;
import com.dong.common.preload.PreloadService;
import com.dong.common.preload.PreloadUtils;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
public class PreloadServiceCsvImpl implements PreloadService {

    @Value("${preload.filename}")
    String preloadFilename;
    @Value("${preload.path}")
    String preloadPath;

    private final ResourceLoader resourceLoader;

    private final PreloadUtils preloadUtils;

    @Override
    public PreloadHandler initPreload() {
        Resource resource = resourceLoader.getResource(preloadUtils.makePath(preloadPath, preloadFilename));
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String[] headers = reader.readNext();
            String location = resource.getFilename();
            return new PreloadHandler(resource,location,headers);
        } catch (IOException ioException) {
            throw new PreloadException("init Fail");
        }
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
