package com.codeup.codeupspringblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeup.codeupspringblog.models.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  Post findPostByTitle(String title);

  Post findPostById(long id);

  @Query("from Post p where p.title like %:term%")
  List<Post> searchByTitleLike(@Param("term") String term);
}