package pl.training.cloud.common.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.cloud.common.Mapper;

@EnableFeignClients(basePackages = "pl.training.cloud")
@EnableDiscoveryClient
@Configuration
public class CommonBeans {

    @Bean
    public Mapper mapper(MessageSource messageSource) {
        return new Mapper(messageSource);
    }

}
