package mmazurkiewicz.controllers;

import mmazurkiewicz.services.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InsertSignController {

    private final BoardService boardService;

    public InsertSignController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/changeSize/{row}/{column}")
    public String changeSize(@PathVariable int row, @PathVariable int column){
        boardService.changeBoardSize(row, column);
        return "redirect:/insert";
    }

    @GetMapping("/insert")
    public String display(Model model){
        model.addAttribute("board", boardService.getBoard());
        model.addAttribute("player", boardService.getCurrentPlayer());
        return "insertSign";
    }

    @GetMapping("/insert/{rowNumber}/{columnNumber}")
    public String insertSign(@PathVariable int rowNumber, @PathVariable int columnNumber){
        if (!boardService.insertSign(rowNumber, columnNumber)){
            return "redirect:/insert";
        }

        if (boardService.checkIfWin(rowNumber, columnNumber)){
            return "redirect:/win/" + boardService.getCurrentPlayer().toString();
        }

        if (boardService.isBoardFilled()){
            return "redirect:/gameOver";
        }

        boardService.changeCurrentPlayer();
        return "redirect:/insert";
    }
}
