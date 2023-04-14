package com.example.pastebin.dto;

import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;
@Data
public class PasteDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String title;
    private String body;
    public static PasteDTO from(Paste paste){
        PasteDTO pasteDTO = new PasteDTO();
        pasteDTO.setId(paste.getId());
        pasteDTO.setTitle(paste.getTitle());
        pasteDTO.setBody(paste.getBody());
        return pasteDTO;
    }
}
