package com.codeup.springblog;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

  @GetMapping("/posts")
  @ResponseBody
  public String postsIndex() {
    return "This is the posts index page";
  }

  @GetMapping("/posts/{id}")
  @ResponseBody
  public String viewPost(@PathVariable Long id) {
    return String.format("Viewing post with ID %d", id);
  }

  @GetMapping("/posts/create")
  @ResponseBody
  public String createPostForm() {
    return "This is the post creation form";
  }

  @PostMapping("/posts/create")
  @ResponseBody
  public String createPost() {
    return "Post created successfully";
  }
}
