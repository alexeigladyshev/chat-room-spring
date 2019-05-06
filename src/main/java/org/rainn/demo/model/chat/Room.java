package org.rainn.demo.model.chat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    private Integer id;
    private String creator;
    private String passcode;

    public Room() {
    }

    public Room(Integer id, String creator, String passcode) {
        this.id = id;
        this.creator = creator;
        this.passcode = passcode;
    }

    public Integer getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
