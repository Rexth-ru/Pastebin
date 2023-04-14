package com.example.pastebin.model;

import com.example.pastebin.en.Access;
import com.example.pastebin.en.Time;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
public class Paste {
    @Id
    private UUID id;
    private String title;
    private String body;
    private Time expirationTime;
    private Access status;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;

}
