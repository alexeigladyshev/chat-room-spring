package org.rainn.demo.web.chat.websockets;

import org.rainn.demo.model.chat.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static java.lang.String.format;

@Controller
public class MessageController {

    @SubscribeMapping("/chat/{room}")
    @SendTo("/chat/{room}")
    public Message onChatroomEntry(@DestinationVariable int room, @RequestParam("roomCode") org.springframework.messaging.Message subscriptionMessage){
        final String username = (String) ((Map<String, Object>) subscriptionMessage.getHeaders().get("simpSessionAttributes")).get("username");
        return new Message("System", format("%s entered room %d", username, room));
    }

    @MessageMapping("/chat/{roomId}/send-message")
    @SendTo("/chat/{roomId}")
    public Message handleIncomingMessage(org.springframework.messaging.Message<String> incoming,
                                         @Payload Message message){
        final String username = (String) ((Map<String, Object>) incoming.getHeaders().get("simpSessionAttributes")).get("username");
        final Integer room = (Integer) ((Map<String, Object>) incoming.getHeaders().get("simpSessionAttributes")).get("entered_room");
        return new Message(format("User %s (room %d)", username, room),
                message.getBody());
    }
}
