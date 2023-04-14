package com.example.pastebin.repository;

import com.example.pastebin.model.Paste;
import com.example.pastebin.projection.PasteProj;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PasteRepository extends JpaRepository<Paste, UUID>, JpaSpecificationExecutor<Paste> {
    List<Paste> findAll(Specification<Paste> specification);
   @Query(value = "select p.title, p.body, p.createDate from Paste p " +
           "where p.status = com.example.pastebin.en.Access.PUBLIC order by p.createDate desc")
    Collection<PasteProj> findPasteTop10();
   void deletePasteByExpiredDateBefore(LocalDateTime localDateTime);
}
