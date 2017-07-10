package pl.training.cloud.organization.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel("departments")
@Data
public class DepartmentsListDto {

    public DepartmentsListDto() {
    }

    public DepartmentsListDto(List<DepartmentDto> departments) {
        this.departments = departments;
    }

    private List<DepartmentDto> departments = new ArrayList<>();

}
