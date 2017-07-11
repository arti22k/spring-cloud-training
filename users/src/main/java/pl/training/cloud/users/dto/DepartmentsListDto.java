package pl.training.cloud.users.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("departments")
@Data
public class DepartmentsListDto {

    List<DepartmentDto> departments;

}
