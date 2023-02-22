package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

  private final List<Post> allPosts = new ArrayList<>();

  public PostController() {
    allPosts.add(new Post(1, "First Post", "This is the first post."));
    allPosts.add(new Post(2, "Second Post", "This is the second post."));
    allPosts.add(new Post(3, "Third Post", "This is the third post."));
  }

  @GetMapping("/posts")
  public String posts(Model model) {
    model.addAttribute("posts", allPosts);
    return "posts/index";
  }

  @GetMapping("/posts/{id}")
  public String showOnePost(@PathVariable long id, Model model) {
    Post post = allPosts.get((int) id - 1);
    model.addAttribute("post", post);
    return "posts/show";
  }

  @GetMapping("/posts/create")
  @ResponseBody
  public String create() {
    return "Create post form";
  }
}
