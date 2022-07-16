package com.alefebarboza.desafiotunts.classdiary.controller;

import com.alefebarboza.desafiotunts.classdiary.service.ClassDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/v1/class-diary")
@RequiredArgsConstructor
public class ClassDiaryController {

    private final ClassDiaryService classDiaryService;

    @GetMapping
    public String readAndWrite() throws GeneralSecurityException, IOException {
        classDiaryService.readAndWrite();
        return "OK";
    }
}
