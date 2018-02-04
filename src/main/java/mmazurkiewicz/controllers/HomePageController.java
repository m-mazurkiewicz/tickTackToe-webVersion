package mmazurkiewicz.controllers;

import mmazurkiewicz.services.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    private BoardService boardService;

    private List<String> posts = new ArrayList<String>() {
        {
            add("Nowa Gra");
            add("Zmień domyśną wielkość planszy");
            add("Wyjdź");
        }
    };

    public HomePageController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String mainMenu(Model model) {
        model.addAttribute("fields",posts);
        model.addAttribute("isGameStarted", boardService.getMovesCounter()>0);
        return "menu";
    }

    @PostMapping(value="/mainMenu", params="insert")
    public String newGame(){
        return "redirect:/insert";
    }

    @PostMapping(value="/mainMenu", params="restartGame")
    public String restartGame(){
        return "redirect:/restart";
    }


    @PostMapping(value="/mainMenu", params="changeDefaultBoardSize")
    public String changeDefaultBoardSize(){
        return "redirect:/changeBoardSize";
    }
}
