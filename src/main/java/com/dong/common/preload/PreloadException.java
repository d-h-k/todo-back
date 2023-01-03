package com.dong.common.preload;

public class PreloadException extends RuntimeException{
    public static final String INIT_FAIL = "init Fail";
    public static final String LAZY_DONG = "Features not yet implemented";
    public PreloadException(String message) {
        super("[preload] Fail to Preload : " + message);
    }
}
