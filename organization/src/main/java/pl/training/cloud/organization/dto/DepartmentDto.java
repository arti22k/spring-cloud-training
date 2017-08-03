package pl.training.cloud.organization.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import pl.training.cloud.organization.entity.StatusType;

@ApiModel("department")
@Data
public class DepartmentDto {

    private String name;
    private StatusType status;

}
