package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.*;
import com.codeup.codeupspringblog.repositories.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
  private final UserRepository userDao;
  private final PasswordEncoder passwordEncoder;

  public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/register")
  public String showSignupForm(Model model){
    model.addAttribute("user", new User());
    return "users/register";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user){
    String hash = passwordEncoder.encode(user.getPassword());
    user.setPassword(hash);
    userDao.save(user);
    return "redirect:/login";
  }

  @GetMapping("/profile")
  public String showProfile() {return "users/profile";}
}