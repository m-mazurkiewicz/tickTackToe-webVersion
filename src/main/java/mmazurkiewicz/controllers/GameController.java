package mmazurkiewicz.controllers;

import mmazurkiewicz.forms.LoadGameForm;
import mmazurkiewicz.services.BoardService;
import mmazurkiewicz.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GameController {

    private final GameService gameService;
    private final BoardService boardService;

    public GameController(GameService gameService, BoardService boardService) {
        this.gameService = gameService;
        this.boardService = boardService;
    }

    @GetMapping("/newGame")
    public String newGame(){
        boardService.changeBoardSize(boardService.getNumberOfRows(), boardService.getNumberOfColumns());
        gameService.newGame(boardService.getNumberOfRows(), boardService.getNumberOfColumns());
        return "redirect:/insert";
    }

    @GetMapping("/loadGame")
    public String displayPage(LoadGameForm loadGameForm){
        return "loadGame";
    }

    @PostMapping("/loadGame")
    public String loadGame(@Valid LoadGameForm loadGameForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "loadGame";
        }

        boardService.loadBoard(gameService.loadGame(loadGameForm.getId())); //todo: zabezpieczyć się przed nieistniejącym id
        return "redirect:/insert";
    }

    @GetMapping("/saveGame")
    public String saveGame(){
       gameService.saveGame(boardService.saveGame(gameService.getCurrentGame()));  //tymczasowo!!!!!

        return "redirect:/insert";
    }

    @GetMapping("/restart")
    public String restartGame(){
        boardService.changeBoardSize(boardService.getNumberOfRows(), boardService.getNumberOfColumns());
        gameService.newGame(boardService.getNumberOfRows(), boardService.getNumberOfColumns());
        return "redirect:/insert";
    }
}
