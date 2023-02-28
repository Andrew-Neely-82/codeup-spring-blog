package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.dao.PostRepository;
import com.codeup.codeupspringblog.dao.UserRepository;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

  private final UserRepository userDao;
  private final PostRepository postDao;

  public PostController(UserRepository userDao, PostRepository postDao) {
    this.userDao = userDao;
    this.postDao = postDao;
  }

  @GetMapping("/posts")
  public String postsHome(Model model) {
    model.addAttribute("posts", postDao.findAll());
    return "posts/index";
  }

  @GetMapping("/posts/search")
  public String showAllPosts(@RequestParam String query, Model model) {
    model.addAttribute("posts", postDao.searchByTitleLike(query));
    return "posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postsHome(@PathVariable long id, Model model) {
    model.addAttribute("post", postDao.findPostById(id));
    model.addAttribute("userIsCreator", true);
    return "posts/show";
  }

  @GetMapping("/posts/create")
  public String postsForm() {
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String createPost(@RequestParam String title, @RequestParam(name = "body") String body) {
    User user = userDao.findUserById(1);
    Post post = new Post(title, body, user);
    postDao.save(post);
    return "redirect:/posts";
  }

}