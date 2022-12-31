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
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
public class PreloadServiceCsvImpl<T> implements PreloadService<T> {

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
        throw new PreloadException("Features not yet implemented");
    }



    @Override
    @Transactional
    public void savePreload(JpaRepository<T, Long> jpaRepository, PreloadHandler preloadHandler, Class<T> saveType) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(preloadHandler.getResource().getInputStream()) )){
            reader.skip(1);
            reader.iterator().forEachRemaining( s-> jpaRepository.save(TypeMapping(saveType,s)));
        } catch (IOException e) {
            throw new PreloadException("no");
        }
    }

    //type mapping error 체크해보기
    private T TypeMapping(Class<T> saveType, String[] s) {
        Class<? extends T> clazz = saveType.asSubclass(saveType);
        Field[] fields = saveType.getDeclaredFields();
        try {
            for(int i=0 ; i<fields.length ; i++) {
                fields[i].set(clazz,s[i]);
            }
            return saveType.cast(clazz);
        }catch (IllegalAccessException exception) {
            throw new PreloadException("TypeMapping Error");
        }
    }

    @Override
    public List<String[]> headerPreloadInfo(PreloadHandler handler) {
        return null;
    }

    @Override
    public void writeAfter(List<String[]> writeData) {

    }
}
