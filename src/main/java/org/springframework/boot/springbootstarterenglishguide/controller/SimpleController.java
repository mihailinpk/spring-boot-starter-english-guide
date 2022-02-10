package org.springframework.boot.springbootstarterenglishguide.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс SimpleController
 * <p/>
 * Контроллер по-умолчанию
 * <p/>
 *
 * @author mihailinpk
 * created 07.02.2022 17:05
 */
@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

}
