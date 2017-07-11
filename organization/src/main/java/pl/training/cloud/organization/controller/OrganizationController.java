package pl.training.cloud.organization.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.cloud.common.Mapper;
import pl.training.cloud.common.controller.UriBuilder;
import pl.training.cloud.common.dto.IdDto;
import pl.training.cloud.organization.dto.DepartmentDto;
import pl.training.cloud.organization.dto.DepartmentsListDto;
import pl.training.cloud.organization.entity.Department;
import pl.training.cloud.organization.service.DepartmentNotFoundException;
import pl.training.cloud.organization.service.OrganizationService;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RequestMapping("departments")
@RestController
public class OrganizationController {

    private OrganizationService organizationService;
    private Mapper mapper;
    private UriBuilder uriBuilder = new UriBuilder();

    @Autowired
    public OrganizationController(OrganizationService organizationService, Mapper mapper) {
        this.organizationService = organizationService;
        this.mapper = mapper;
    }

    @ApiOperation("Create new department")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        long id = organizationService.addDepartment(department).getId();
        URI uri = uriBuilder.requestUriWithId(id);
        return created(uri).build();
    }

    @ApiOperation(value = "Get department with id", response = DepartmentDto.class)
    @RequestMapping(value = "{department-id}", method = RequestMethod.GET)
    public DepartmentDto getDepartmentById(@PathVariable("department-id") Long id) {
        Department department = organizationService.getDepartment(id);
        return mapper.map(department, DepartmentDto.class);
    }

    @ApiOperation(value = "Get all departments", response = DepartmentDto.class)
    @RequestMapping(method = RequestMethod.GET)
    public DepartmentsListDto getDepartments(@RequestParam(name = "name", required = false) String name) {
        List<Department> departments = name == null ? organizationService.getDepartments()
                : organizationService.getDepartmentsByName(name);
        return new DepartmentsListDto(mapper.map(departments, DepartmentDto.class));
    }

    @ApiOperation(value = "Update department")
    @RequestMapping(value = "{department-id}", method = RequestMethod.PUT)
    public ResponseEntity updateDepartment(@PathVariable("department-id") Long id,
                                 @RequestBody DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        department.setId(id);
        organizationService.updateDepartment(department);
        return noContent().build();
    }

    @ApiOperation(value = "Delete department")
    @RequestMapping(value = "{department-id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDepartment(@PathVariable("department-id") Long id) {
        organizationService.deleteDepartment(id);
        return noContent().build();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity onOrganizationNotFound(DepartmentNotFoundException ex, Locale locale) {
        return new ResponseEntity(mapper.map(ex, locale), HttpStatus.NOT_FOUND);
    }

}
