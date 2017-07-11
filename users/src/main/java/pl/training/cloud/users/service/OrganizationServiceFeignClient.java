package pl.training.cloud.users.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.training.cloud.users.dto.DepartmentsListDto;

@FeignClient("organization")
public interface OrganizationServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "departments")
    DepartmentsListDto getDepartments(@RequestParam("name") String name);

}
