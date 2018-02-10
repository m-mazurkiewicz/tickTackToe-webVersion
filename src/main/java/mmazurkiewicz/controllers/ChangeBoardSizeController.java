package mmazurkiewicz.controllers;

import mmazurkiewicz.forms.ChangeBoardSizeForm;
import mmazurkiewicz.services.BoardService;
import mmazurkiewicz.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class ChangeBoardSizeController {

    private final BoardService boardService;

    public ChangeBoardSizeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/changeBoardSize")
    public String displayPage(ChangeBoardSizeForm changeBoardSizeForm){
        return "changeBoardSize";
    }

    @PostMapping("/changeBoardSize")
    public String getNewSize(@Valid ChangeBoardSizeForm changeBoardSizeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "changeBoardSize";
        }
        return "redirect:/changeSize/"+changeBoardSizeForm.getX()+"/"+changeBoardSizeForm.getY();
    }

    @GetMapping("/changeSize/{row}/{column}")
    public String changeSize(@PathVariable int row, @PathVariable int column){
        boardService.changeBoardSize(row, column);
        return "redirect:/";
    }
}

