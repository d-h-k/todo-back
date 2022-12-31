package com.dong.common.preload;

public class PreloadException extends RuntimeException{
    public PreloadException(String message) {
        super("[preload] Fail to Preload : " + message);
    }
}
