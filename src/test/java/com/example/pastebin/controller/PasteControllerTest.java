package com.example.pastebin.controller;

import com.example.pastebin.en.Access;
import com.example.pastebin.en.Time;
import com.example.pastebin.model.Paste;
import com.example.pastebin.repository.PasteRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PasteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasteRepository pasteRepository;
    private final Paste paste=new Paste();
    private final JSONObject jsonObject = new JSONObject();
    private final Collection<Paste> pastes = new ArrayList<>();
    @BeforeEach
    void setUp() throws JSONException {
        paste.setId(UUID.randomUUID());
        paste.setTitle("test");
        paste.setBody("test,test");
        paste.setStatus(Access.PUBLIC);
        paste.setExpirationTime(Time.NO_LIMITS);
        paste.setCreateDate(LocalDateTime.now());
        pasteRepository.save(paste);
        Paste paste1 = new Paste();
        paste1.setId(UUID.randomUUID());
        paste1.setTitle("test1");
        paste1.setBody("test1,test1");
        paste1.setStatus(Access.PUBLIC);
        paste1.setExpirationTime(Time.TEN_MINUTE);
        paste1.setCreateDate(LocalDateTime.now());
        pasteRepository.save(paste1);
        pastes.add(paste);
        pastes.add(paste1);

        jsonObject.put("title","test2");
        jsonObject.put("body","test2test");
        jsonObject.put("status",Access.PUBLIC);
        jsonObject.put("expirationTime",Time.NO_LIMITS);


    }
    @Test
    void newPaste() throws Exception {
        mockMvc.perform(post("/paste")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getTenLastPaste() throws Exception {
        mockMvc.perform(get("/paste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void getPastTitleOrBody() throws Exception {
        mockMvc.perform(get("/paste/{text}",paste.getTitle()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}