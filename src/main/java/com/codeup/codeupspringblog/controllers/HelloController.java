package com.codeup.codeupspringblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  // Returns "Hello World"
  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  // Returns "Hello {name}!"
  @GetMapping("/hello/{name}")
  public String sayHello(@PathVariable String name) {
    return "Hello " + name + "!";
  }

  // Adds one to the given number and returns the result
  @GetMapping("/increment/{number}")
  public String addOne(@PathVariable int number) {
    return String.format("%d plus 1 is %d", number, number + 1);
  }

  @GetMapping("/home")
    public String welcome() {
        return "home";
    }

}