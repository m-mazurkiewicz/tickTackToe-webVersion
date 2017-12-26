package mmazurkiewicz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EndGameController {

    @GetMapping("/win")
    public String gameWon(@RequestParam String mark, Model model){
        model.addAttribute("winnerPlayer", mark);
        return "winPage";
    }

    @GetMapping("/gameOver")
    public String drawGame(){
        return "gameOver";
    }
}
