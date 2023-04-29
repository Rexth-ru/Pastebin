package com.example.pastebin.service;

import com.example.pastebin.dto.CreatePaste;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.model.Paste;
import com.example.pastebin.projection.PasteProj;
import com.example.pastebin.repository.PasteRepository;
import com.example.pastebin.repository.specification.Spec;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;
    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    public String createPaste(CreatePaste createPaste){
        Paste paste = createPaste.to();
        paste.setCreateDate(Instant.now());
        paste.setExpiredDate(Instant.now().plus(createPaste.getExpirationTime().getTime(),createPaste.getExpirationTime().getChronoUnit()));
        pasteRepository.save(paste);
        createPaste.setId(paste.getId());
        return createPaste.getId();
    }
    public Collection<PasteDTO> getTenLastPast(){
        Collection<PasteProj> pasteProjs = pasteRepository.findPasteTop10();
        if (pasteProjs.isEmpty())throw new EntityNotFoundException();
        return pasteProjs.stream()
                .map(PasteProj::from).collect(Collectors.toList());
    }
    public Collection<PasteDTO> getPastByTitleOrBody(String text){
        if (text.isEmpty()) return null;
        Collection<Paste> pastes = pasteRepository.findAll(
                Spec.byTitle(text).or(Spec.byBody(text)));
        pastes.stream().map(paste->{
            if (!validPaste(paste))return null;
            return paste;
        }).collect(Collectors.toList());
        if (pastes.isEmpty())throw new EntityNotFoundException();
        return pastes.stream().map(PasteDTO::from).collect(Collectors.toList());
    }
    public PasteDTO getPastByLink(String id){
        Paste paste = pasteRepository.findByLink(id);
        if (paste==null) return null;
        if (!validPaste(paste))return null;
        return PasteDTO.from(paste);
    }
    private boolean validPaste(Paste paste) {
        if (paste.getExpiredDate().isAfter(Instant.now()))return true;
        return false;
    }
}
