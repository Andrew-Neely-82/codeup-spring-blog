package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.dao.PostRepository;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

  private final PostRepository postDao;

  public PostController(PostRepository postDao) {
    this.postDao = postDao;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("posts", postDao.findAll());
    return "posts/index";
  }

  @GetMapping("/search")
  public String search(@RequestParam String query, Model model) {
    model.addAttribute("posts", postDao.searchByTitleLike(query));
    return "posts/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable long id, Model model) {
    Post post = postDao.findById(id).orElse(null);
    if (post == null) {
      return "redirect:/posts";
    }
    model.addAttribute("post", post);
    return "posts/show";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("post", new Post());
    return "posts/create";
  }

  @PostMapping("/create")
  public String save(@Validated @ModelAttribute("post") Post post, BindingResult result) {
    if (result.hasErrors()) {
      return "posts/create";
    }
    postDao.save(post);
    return "redirect:/posts/" + post.getId();
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable long id, Model model) {
    Post post = postDao.findById(id).orElse(null);
    if (post == null) {
      return "redirect:/posts";
    }
    model.addAttribute("post", post);
    return "posts/edit";
  }

  @PostMapping("/{id}/edit")
  public String update(@Validated @ModelAttribute("post") Post post, BindingResult result) {
    if (result.hasErrors()) {
      return "posts/edit";
    }
    postDao.save(post);
    return "redirect:/posts/" + post.getId();
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable long id) {
    postDao.deleteById(id);
    return "redirect:/posts";
  }

  @GetMapping("/{id}/editPost")
  public String editPost(@PathVariable Long id, Model model) {
    Post post = postDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
    model.addAttribute("post", post);
    return "posts/edit";
  }

}