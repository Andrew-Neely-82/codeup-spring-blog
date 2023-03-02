package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;


@Service("EmailService")
public class EmailService {

  @Autowired
  public JavaMailSender javaMailSender;

  @Value("${spring.mail.from}")
  private String from;

  @Value("${CUSTOM_KEY}")
  private String customKey;

  public void preparedAndSendAd(Ad ad){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(ad.getUser().getEmail());
    message.setSubject("Ad created");
    message.setText(String.format("Ad title: '%s'%nAd description: '%s'", ad.getTitle(), ad.getDescription()));

    try{
      this.javaMailSender.send(message);
    } catch(MailException ex){
      System.out.println(ex.getMessage());
    }
  }

  public void preparedAndSendPost(Post post){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(post.getUser().getEmail());
    message.setSubject("Post created");
    message.setText(String.format("Post title: '%s'%nPost body: '%s'", post.getTitle(), post.getBody()));

    try{
      this.javaMailSender.send(message);
    } catch(MailException ex){
      System.out.println(ex.getMessage());
    }
  }
}