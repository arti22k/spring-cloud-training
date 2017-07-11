package pl.training.cloud.organization.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("department")
@Data
public class DepartmentDto {

    private Long id;
    private String name;

}
