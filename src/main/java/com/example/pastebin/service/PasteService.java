package com.example.pastebin.service;

import com.example.pastebin.dto.CreatePaste;
import com.example.pastebin.dto.PasteDTO;
import com.example.pastebin.model.Paste;
import com.example.pastebin.projection.PasteProj;
import com.example.pastebin.repository.PasteRepository;
import com.example.pastebin.repository.specification.Spec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;
    @Value(value ="${pastebin.url}")
    private String siteUrl;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    public String createPaste(CreatePaste createPaste){
        Paste paste = createPaste.to();
        pasteRepository.save(paste);
        createPaste.setId(paste.getId());
        return siteUrl+createPaste.getId().toString();
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
        if (pastes.isEmpty())throw new EntityNotFoundException();
        return pastes.stream().map(PasteDTO::from).collect(Collectors.toList());
    }
}
