package com.example.pastebin.dto;

import com.example.pastebin.en.Access;
import com.example.pastebin.en.Time;
import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;
@Data
public class CreatePaste {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String title;
    private String body;
    private Time expirationTime;
    private Access status;
    public static CreatePaste from(Paste paste){
        CreatePaste createPaste = new CreatePaste();
        createPaste.setId(paste.getId());
        createPaste.setTitle(paste.getTitle());
        createPaste.setBody(paste.getBody());
        createPaste.setExpirationTime(paste.getExpirationTime());
        createPaste.setStatus(paste.getStatus());
        return createPaste;
    }

    public Paste to(){
        Paste paste = new Paste();
        paste.setId(UUID.randomUUID());
        paste.setTitle(this.getTitle());
        paste.setBody(this.getBody());
        paste.setExpirationTime(this.getExpirationTime());
        paste.setStatus(this.getStatus());
        return paste;
    }
}
