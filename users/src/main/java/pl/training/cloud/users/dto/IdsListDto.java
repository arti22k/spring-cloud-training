package pl.training.cloud.users.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import pl.training.cloud.common.dto.IdDto;

import java.util.List;

@ApiModel("ids")
@Data
public class IdsListDto {

    List<IdDto> departments;

}
