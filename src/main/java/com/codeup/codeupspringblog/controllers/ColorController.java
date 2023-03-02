package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColorController {
  @GetMapping("/fav-color")
  public String favColorForm() {
    return "fav-color";
  }

  @PostMapping("/fav-color")
  @ResponseBody
  public String whatWasTheFavoriteColor(@RequestParam(name = "color") String color) {
    return String.format("User really likes the color: %s.", color);
  }
}