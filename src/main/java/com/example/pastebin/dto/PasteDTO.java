package com.example.pastebin.dto;

import com.example.pastebin.model.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class PasteDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
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
