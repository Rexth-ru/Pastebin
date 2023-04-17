package com.example.pastebin.projection;

import com.example.pastebin.dto.PasteDTO;

public interface PasteProj {
    String getId();

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
