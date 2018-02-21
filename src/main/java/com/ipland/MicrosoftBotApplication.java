package com.ipland;

import com.ipland.bot.rest.auth.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Created by Ivan Zbykovskyi on 1/31/18.
 */
@SpringBootApplication
@EnableAsync
public class MicrosoftBotApplication {

    @Inject
    private AuthenticationService authenticationService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MicrosoftBotApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate rt = builder.build();
        rt.getMessageConverters().add(new StringHttpMessageConverter());
        rt.setRequestFactory(new BufferingClientHttpRequestFactory(rt.getRequestFactory()));
        return rt;
    }

    @Bean("threadPoolAppReadyAsyncTaskExecutor")
    public TaskExecutor threadPoolAppReadyAsyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(5);
        return executor;
    }

    @EventListener
    public void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        authenticationService.handleApplicationReadyEventRefreshAccessToken();
        authenticationService.handleApplicationReadyEventRefreshListSigningKeys();
    }
}