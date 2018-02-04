package mmazurkiewicz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EndGameController {

//    @GetMapping("/win")
//    public String gameWon(@RequestParam String mark, Model model){
//        model.addAttribute("winnerPlayer", mark);
//        return "winPage";
//    }

    @GetMapping("/win/{player}")
    public String gameWon(@PathVariable String player, Model model){
        model.addAttribute("winnerPlayer", player);
        return "winPage";
    }

    @GetMapping("/gameOver")
    public String drawGame(){
        return "gameOver";
    }
}
