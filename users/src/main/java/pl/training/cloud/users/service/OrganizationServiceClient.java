package pl.training.cloud.users.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.dto.IdsListDto;

import java.util.List;
import java.util.Optional;

public class OrganizationServiceClient {

    private DiscoveryClient discoveryClient;

    public OrganizationServiceClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public Optional<Long> getDepartmentId(String departmentName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("organization");
        if (serviceInstances.isEmpty()) {
            return Optional.empty();
        }
        String serviceUri = serviceInstances.get(0).getUri().toString() + "/departments?name=" + departmentName;
        IdsListDto idsListDto = new RestTemplate().exchange(serviceUri, HttpMethod.GET, null, IdsListDto.class).getBody();
        if (idsListDto.getDepartments().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(idsListDto.getDepartments().get(0).getId());
    }

}
