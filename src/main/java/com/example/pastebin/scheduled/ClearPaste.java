package com.example.pastebin.scheduled;

import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
@Slf4j
@Component
@Data
public class ClearPaste {
    private final PasteRepository pasteRepository;
    private Paste paste;

    @Scheduled(fixedDelay = 10)
    @Transactional
    public void clearPaste() {
        log.info("Clear paste");
        if (paste.getExpiredDate().isBefore(Instant.now()))
            pasteRepository.deletePasteByExpiredDateBefore(Instant.now());
    }
}
