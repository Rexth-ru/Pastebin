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
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

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
    private final Paste paste = new Paste();
    private final Paste paste2 = new Paste();
    private final JSONObject jsonObject = new JSONObject();
    private final Collection<Paste> pastes = new ArrayList<>();

    @BeforeEach
    void setUp() throws JSONException {

        paste.setId(RandomStringUtils.randomAlphanumeric(8));
        paste.setTitle("test");
        paste.setBody("test,test");
        paste.setStatus(Access.PUBLIC);
        paste.setExpirationTime(Time.ONE_HOUR);
        paste.setExpiredDate(Instant.now().plus(Time.ONE_HOUR.getTime(), Time.ONE_HOUR.getChronoUnit()));
        paste.setCreateDate(Instant.now());
        pasteRepository.save(paste);

        Paste paste1 = new Paste();
        paste1.setId(RandomStringUtils.randomAlphanumeric(8));
        paste1.setTitle("test1");
        paste1.setBody("test1,test1");
        paste1.setStatus(Access.PUBLIC);
        paste1.setExpirationTime(Time.TEN_MINUTE);
        paste1.setExpiredDate(Instant.now().plus(Time.TEN_MINUTE.getTime(), Time.TEN_MINUTE.getChronoUnit()));
        paste1.setCreateDate(Instant.now());
        pasteRepository.save(paste1);

        paste2.setId(RandomStringUtils.randomAlphanumeric(8));
        paste2.setTitle("test3");
        paste2.setBody("test3,test3");
        paste2.setStatus(Access.UNLISTED);
        paste2.setExpirationTime(Time.ONE_MONTH);
        paste2.setExpiredDate(Instant.now().plus(Time.ONE_MONTH.getTime(), Time.ONE_MONTH.getChronoUnit()));
        paste2.setCreateDate(Instant.now());
        pasteRepository.save(paste2);

        pastes.add(paste);
        pastes.add(paste1);
        pastes.add(paste2);

        jsonObject.put("title", "test2");
        jsonObject.put("body", "test2test");
        jsonObject.put("status", Access.PUBLIC);
        jsonObject.put("expirationTime", Time.THREE_HOUR);
    }

    @BeforeEach
    void clearPaste() {
        pasteRepository.deleteAll();
    }

    @Test
    void newPaste() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin.tld")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getTenLastPaste() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void getPastTitleOrBody() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/{text}", paste.getTitle()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    void getPasteUnlisted() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin.tld/code/{id}", paste2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(paste2.getId()));
    }
}