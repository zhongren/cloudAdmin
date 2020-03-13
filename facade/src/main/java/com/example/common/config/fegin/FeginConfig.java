package com.example.common.config.fegin;

import feign.Logger;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeginConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder().retryOnConnectionFailure(true).connectionPool(pool())
                .connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(100000, TimeUnit.SECONDS)
                .eventListener(new EventListener() {
                    @Override
                    public void callStart(Call call) {
                        //System.out.println("----------okhttp");
                        super.callStart(call);
                    }
                })
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(50, 5, TimeUnit.MINUTES);
    }
}
