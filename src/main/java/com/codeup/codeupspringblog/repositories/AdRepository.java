package com.codeup.codeupspringblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeup.codeupspringblog.models.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
  Ad findAdByTitle(String title);

  Ad findAdById(long id);

  @Query("from Ad a where a.title like %:term%")
  List<Ad> searchByTitleLike(@Param("term") String term);
}