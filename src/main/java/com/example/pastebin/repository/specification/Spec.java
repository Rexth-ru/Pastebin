//package com.example.pastebin.repository.specification;
//
//import com.example.pastebin.model.Paste;
//import org.springframework.data.jpa.domain.Specification;
//
//
//public class Spec {
//    public static Specification<Paste> byTitle(String title) {
//        return ((root, query, criteriaBuilder) -> {
//            if (title==null||title.isEmpty())return criteriaBuilder.conjunction();
//            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
//        });
//    }
//    public static Specification<Paste> byBody(String body) {
//        return ((root, query, criteriaBuilder) -> {
//            if (body==null||body.isEmpty())return criteriaBuilder.conjunction();
//            return criteriaBuilder.like(root.get("title"), "%" + body + "%");
//        });
//    }
//}
