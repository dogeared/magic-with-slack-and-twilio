package com.afitnerd.magic.controller;

import com.afitnerd.magic.service.MagicCardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class WelcomeController {

    private MagicCardService magicCardService;

    public WelcomeController(MagicCardService magicCardService) {
        this.magicCardService = magicCardService;
    }

    @RequestMapping("/")
    public String welcome(Model model) throws IOException {
        model.addAttribute("randomMagicCardUrl", magicCardService.getRandomMagicCardImageUrl());
        return "welcome";
    }
}
