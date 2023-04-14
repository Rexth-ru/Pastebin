package com.example.pastebin.controller;

import com.example.pastebin.dto.CreatePaste;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.service.PasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paste")
public class PasteController {
    private final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }
    @PostMapping
    public ResponseEntity<?> newPaste(CreatePaste createPaste){
        return ResponseEntity.ok(pasteService.createPaste(createPaste));
    }
    @GetMapping
    public ResponseEntity<?> getTenLastPaste(){
        return ResponseEntity.ok(pasteService.getTenLastPast());
    }
    @GetMapping("/{text}")
    public ResponseEntity<?> getPasteTitleOrBody(@PathVariable String text){
         return ResponseEntity.ok(pasteService.getPastByTitleOrBody(text));
    }
}
