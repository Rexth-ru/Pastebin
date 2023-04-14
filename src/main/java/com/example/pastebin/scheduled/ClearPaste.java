package com.example.pastebin.scheduled;

import com.example.pastebin.en.Time;
import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Slf4j
@Component
public class ClearPaste {
    private final PasteRepository pasteRepository;
    private Paste paste;

    public ClearPaste(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }
    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void clearPaste(){
        log.info("Clear paste");
        if (paste.getExpirationTime().equals(Time.ONE_HOUR)) {
            paste.setExpiredDate(paste.getCreateDate().plusHours(1));
            pasteRepository.deletePasteByExpiredDateBefore(LocalDateTime.now());
        }
        if (paste.getExpirationTime().equals(Time.TEN_MINUTE)) {
            paste.setExpiredDate(paste.getCreateDate().plusMinutes(10));
            pasteRepository.deletePasteByExpiredDateBefore(LocalDateTime.now());
        }
        if (paste.getExpirationTime().equals(Time.THREE_HOUR)) {
            paste.setExpiredDate(paste.getCreateDate().plusHours(3));
            pasteRepository.deletePasteByExpiredDateBefore(LocalDateTime.now());
        }
        if (paste.getExpirationTime().equals(Time.ONE_MONTH)) {
            paste.setExpiredDate( paste.getCreateDate().plusMonths(1));
            pasteRepository.deletePasteByExpiredDateBefore(LocalDateTime.now());
        }
        if (paste.getExpirationTime().equals(Time.ONE_WEEK)) {
            paste.setExpiredDate(paste.getCreateDate().plusWeeks(1));
            pasteRepository.deletePasteByExpiredDateBefore(LocalDateTime.now());
        }
    }
}
