package org.rainn.demo.web.chat.http;

import org.rainn.demo.model.chat.Room;
import org.rainn.demo.web.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

@Controller
public class ChatroomController {
    @Inject
    private RoomRepository roomRepository;

    @PostMapping("enter-chatroom")
    public String enterChatroom(@RequestParam("chatroomId") Integer chatroomId, HttpSession userSession, @RequestParam("username") String userName,
                                @RequestParam(value = "passcode", required = false) String passcode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userSession.setAttribute("entered_room", chatroomId);
        userSession.setAttribute("username", userName);
        Optional<Room> byId = roomRepository.findRoomByIdAndPasscode(chatroomId, passcode);
        if (!byId.isPresent()) {
            byId = roomRepository.findById(chatroomId);
            if (!byId.isPresent()) {
                final Room newRoom = new Room(chatroomId, userName, passcode);
                roomRepository.save(newRoom);
                byId = Optional.of(newRoom);
            }
        } else {
            return format("redirect:chatroom/%d", chatroomId);
        }

        // if (byId.get().getPasscode() == null || byId.get().getCreator() == userName) {
        if (byId.get().getCreator().equals(userName) || byId.get().getPasscode().equals(passcode) ) {
            return format("redirect:chatroom/%d", chatroomId);
        } else {
            response.sendError(401, "Invalid passcode for "+chatroomId.toString()+" room."); 
            return null;
        }

    }

    @GetMapping("chatroom/{roomId}")
    public ModelAndView chatroom(@PathVariable Integer roomId, ModelAndView modelAndView) {
        modelAndView.addObject("chatroomId", roomId);
        modelAndView.setViewName("chatroom");
        return modelAndView;
    }
}
