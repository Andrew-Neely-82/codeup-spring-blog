package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeup.codeupspringblog.repositories.AdRepository;

@Controller
public class AdController {

  private final AdRepository adDao;

  public AdController(AdRepository adDao) {
    this.adDao = adDao;
  }

  @GetMapping("/ads")
  public String showAllAds(Model model) {
    model.addAttribute("ads", adDao.findAll());
    return "ads/index";
  }

  @GetMapping("/ads/search")
  public String showAllAds(@RequestParam String query, Model model) {
    model.addAttribute("ads", adDao.searchByTitleLike(query));
    return "ads/index";
  }

  @GetMapping("/ads/{id}")
  public String getOneAd(@PathVariable long id, Model model) {
    model.addAttribute("ad", adDao.findAdById(id));
    model.addAttribute("userIsCreator", true);
    return "ads/show";
  }
}