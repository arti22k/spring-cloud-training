package pl.training.cloud.users.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.dto.DepartmentsListDto;

import java.util.List;
import java.util.Optional;

public class OrganizationServiceClient {

    /*
    Implementation 1

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
        DepartmentsListDto departmentsListDto = new RestTemplate().exchange(serviceUri, HttpMethod.GET, null, DepartmentsListDto.class).getBody();
        if (departmentsListDto.getDepartments().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(departmentsListDto.getDepartments().get(0).getId());
    }
    */

    private RestTemplate restTemplate;

    public OrganizationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Long> getDepartmentId(String departmentName) {
        String serviceUri = "http://organization/departments?name=" + departmentName;
        DepartmentsListDto departmentsListDto = restTemplate.exchange(serviceUri, HttpMethod.GET, null, DepartmentsListDto.class).getBody();
        if (departmentsListDto.getDepartments().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(departmentsListDto.getDepartments().get(0).getId());
    }

}
