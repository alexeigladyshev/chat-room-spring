package org.rainn.demo.model.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String senderName;
    private String body;

    @JsonCreator
    public Message(@JsonProperty("senderName") String senderName, @JsonProperty("body") String body) {
        this.senderName = senderName;
        this.body = body;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
