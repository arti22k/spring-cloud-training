package pl.training.cloud.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exception")
@Getter
@Setter
public class ExceptionDto {

    private String description;

    public ExceptionDto() {
    }

    public ExceptionDto(String description) {
        this.description = description;
    }

}