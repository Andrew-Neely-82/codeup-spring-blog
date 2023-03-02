package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.*;
import com.codeup.codeupspringblog.repositories.*;
import com.codeup.codeupspringblog.services.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
  private final PostRepository postDao;
  private final UserRepository userDao;
  private final EmailService emailService;

  public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
    this.postDao = postDao;
    this.userDao = userDao;
    this.emailService = emailService;
  }

  @GetMapping("/posts")
  public String postsHome(Model model) {
    List<Post> posts = postDao.findAll();
    model.addAttribute("posts", posts);
    return "posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postsHome(@PathVariable long id, Model model) {
    Post post = postDao.findPostById(id);
    model.addAttribute("post", post);
    return "posts/show";
  }

  @GetMapping("/posts/create")
  public String postsForm(Model model) {
    model.addAttribute("post", new Post());
    model.addAttribute("heading", "Create new post.");
    return "posts/create";
  }

  @PostMapping("/posts/save")
  public String createPost(@ModelAttribute Post post) {
    User user = userDao.findById(1);
    post.setUser(user);
    postDao.save(post);
    emailService.preparedAndSendPost(post);
    return "redirect:/posts";
  }

  @GetMapping("/posts/{id}/edit")
  public String editPostForm(Model model, @PathVariable long id) {
    model.addAttribute("post", postDao.findPostById(id));
    model.addAttribute("heading", "Edit post.");
    return "posts/create";
  }
}