package com.dong.common.config;

import com.dong.account.service.AccountService;
import com.dong.common.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ServerConfigure implements WebMvcConfigurer {
    public static final String ACCOUNT_URL = "/api/v1/account";
    public static final String ARTICLE_URL = "/api/v1/articles";

    @Autowired
    private final AccountService accountService;

    private static final List<String> URL_PATTERNS
            = Arrays.asList(ACCOUNT_URL,ARTICLE_URL);

    //인터셉터 주소 세팅
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(accountService)).addPathPatterns(URL_PATTERNS);
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns(ARTICLE_URL);
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/v1/account/*");
    }



}
