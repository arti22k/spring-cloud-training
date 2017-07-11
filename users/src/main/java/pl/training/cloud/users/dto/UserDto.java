package pl.training.cloud.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(value = "User")
@Data
public class UserDto {

    @ApiModelProperty(required = true)
    private String login;
    @JsonProperty(access = Access.WRITE_ONLY)
    @ApiModelProperty(required = true)
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String department;

}
