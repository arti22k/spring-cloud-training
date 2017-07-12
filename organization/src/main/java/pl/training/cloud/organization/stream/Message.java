package pl.training.cloud.organization.stream;

import lombok.Data;

@Data
public class Message {

    private Long id;
    private String text;

    public Message() {
    }

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

}
