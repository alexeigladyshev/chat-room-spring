package org.rainn.demo.web;

import org.rainn.demo.model.chat.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This is a simulation of a Spring JPA repository.
 */
@Component
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Optional<Room> findRoomByIdAndPasscode(int id, String passcode);
}
