package pl.training.cloud.users.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.cloud.common.Mapper;
import pl.training.cloud.common.controller.UriBuilder;
import pl.training.cloud.users.dto.*;
import pl.training.cloud.users.entity.User;
import pl.training.cloud.users.repository.ResultPage;
import pl.training.cloud.users.service.OrganizationServiceClient;
import pl.training.cloud.users.service.OrganizationServiceFeignClient;
import pl.training.cloud.users.service.UsersService;
import pl.training.cloud.users.stream.EventEmitter;
import pl.training.cloud.users.stream.Message;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Api(description = "Users resource")
@RequestMapping(value = "users")
@RestController
public class UsersController {

    private Mapper mapper;
    private UsersService usersService;
    private OrganizationServiceClient organizationServiceClient;
    private OrganizationServiceFeignClient organizationServiceFeignClient;
    private EventEmitter eventEmitter;
    private UriBuilder uriBuilder = new UriBuilder();

    @Autowired
    public UsersController(Mapper mapper, UsersService usersService, OrganizationServiceClient organizationServiceClient,
                           OrganizationServiceFeignClient organizationServiceFeignClient, EventEmitter eventEmitter) {
        this.mapper = mapper;
        this.usersService = usersService;
        this.organizationServiceClient = organizationServiceClient;
        this.organizationServiceFeignClient = organizationServiceFeignClient;
        this.eventEmitter = eventEmitter;
    }

    @ApiOperation(value = "Create new user")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@ApiParam(name = "user") @RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
//        Optional<Long> departmentId = organizationServiceClient.getDepartmentId(userDto.getDepartment());
//        if (departmentId.isPresent()) {
//            user.setDepartmentId(departmentId.get());
//        }
        if (userDto.getDepartment() != null) {
            DepartmentsListDto departmentsListDto = organizationServiceFeignClient.getDepartments(userDto.getDepartment());
            List<DepartmentDto> departmentDtos = departmentsListDto.getDepartments();
            if (!departmentDtos.isEmpty()) {
                user.setDepartmentId(departmentDtos.get(0).getId());
            }
        }
        usersService.addUser(user);
        URI uri = uriBuilder.requestUriWithId(user.getId());
        return created(uri).build();
    }

    @ApiOperation(value = "Get users", response = UsersPageDto.class)
    @RequestMapping(method = RequestMethod.GET)
    public UsersPageDto getUsers(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "pageSize") int pageSize) {
        ResultPage<User> resultPage = usersService.getUsers(pageNumber, pageSize);
        List<UserDto> usersDtos = mapper.map(resultPage.getContent(), UserDto.class);
        return new UsersPageDto(usersDtos, resultPage.getPageNumber(), resultPage.getTotalPages());
    }

    @ApiOperation(value = "Send new Message")
    @RequestMapping(method = RequestMethod.POST, value = "messages")
    public ResponseEntity sendMessage(@RequestBody  MessageDto messageDto) {
        Message message = mapper.map(messageDto, Message.class);
        eventEmitter.emit(message);
        return ok().build();
    }

}
