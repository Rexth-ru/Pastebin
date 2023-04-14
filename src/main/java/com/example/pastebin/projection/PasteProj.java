package com.example.pastebin.projection;

import com.example.pastebin.dto.PasteDTO;

import java.util.Date;
import java.util.UUID;

public interface PasteProj {
    UUID getId();

    String getTitle();

    String getBody();

    default PasteDTO from() {
        PasteDTO pasteDTO = new PasteDTO();
        pasteDTO.setId(getId());
        pasteDTO.setTitle(getTitle());
        pasteDTO.setBody(getBody());
        return pasteDTO;
    }
}
