package com.example.pastebin.dto;

import com.example.pastebin.en.Access;
import com.example.pastebin.en.Time;
import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;
@Data
public class CreatePaste {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String title;
    private String body;
    private Time expirationTime;
    private Access status;

    public Paste to(){
        Paste paste = new Paste();
        paste.setId(RandomStringUtils.randomAlphanumeric(8));
        paste.setTitle(this.getTitle());
        paste.setBody(this.getBody());
        paste.setExpirationTime(this.getExpirationTime());
        paste.setStatus(this.getStatus());
        return paste;
    }
}
