package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColorController {
  @GetMapping("/fav-color")
  public String favColor() {
    return "fav-color";
  }

  @PostMapping("/fav-color")
  @ResponseBody
  public String submittedColor(@RequestParam(name = "color") String color) {
    return String.format("User Color: %s", color);
  }
}