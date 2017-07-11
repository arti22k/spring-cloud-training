package pl.training.cloud.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.repository.UsersRepository;
import pl.training.cloud.users.service.OrganizationServiceClient;
import pl.training.cloud.users.service.UsersService;

@ComponentScan(basePackages = "pl.training.cloud.common.config")
@Configuration
public class Beans {

    @Bean
    public UsersService usersService(UsersRepository usersRepository) {
        return new UsersService(usersRepository);
    }

    /*
    // Implementation 1

    @Bean
    public OrganizationServiceClient organizationService(DiscoveryClient discoveryClient) {
        return new OrganizationServiceClient(discoveryClient);
    }
    */

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OrganizationServiceClient organizationService(RestTemplate restTemplate) {
        return new OrganizationServiceClient(restTemplate);
    }

}
