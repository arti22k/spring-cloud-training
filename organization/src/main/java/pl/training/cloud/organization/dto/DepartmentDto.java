package pl.training.cloud.organization.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@ApiModel("department")
@XmlRootElement(name = "department")
@Data
public class DepartmentDto {

    private String name;

}
