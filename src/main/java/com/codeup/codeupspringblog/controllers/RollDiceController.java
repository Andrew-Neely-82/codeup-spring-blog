package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class RollDiceController {

  @GetMapping("/roll-dice")
  public String showRollDicePage() {
    return "roll-dice";
  }

  @GetMapping("/roll-dice/{guess}")
  public String handleRollDiceGuess(@PathVariable int guess, Model model) {
    Random rand = new Random();
    int diceRoll = rand.nextInt(6) + 1;
    int numMatches = (diceRoll == guess) ? 1 : 0;
    String message = String.format("You guessed %d. The roll was %d. You %s!", guess, diceRoll,
        (numMatches > 0) ? "matched" : "didn't match");
    model.addAttribute("guess", guess);
    model.addAttribute("diceRoll", diceRoll);
    model.addAttribute("message", message);
    return "roll-dice";
  }
}
