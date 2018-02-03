package mmazurkiewicz.controllers;

//import mmazurkiewicz.forms.InsertSignForm;
//import mmazurkiewicz.models.Board;
import mmazurkiewicz.models.Mark;
import mmazurkiewicz.repositories.RowsRepository;
import mmazurkiewicz.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InsertSignController {

    private final BoardService boardService;

    public InsertSignController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String display(Model model){
        model.addAttribute("board", boardService.getBoard());
        return "insertSign";
    }

    @GetMapping("/insert/{rowNumber}/{columnNumber}")
    public String insertSign(@PathVariable int rowNumber, @PathVariable int columnNumber){
        boardService.insertSign(rowNumber, columnNumber);
        if (boardService.isBoardFilled()){
            return "redirect:/gameOver";
        }
        return "redirect:/";
    }

    /*    @GetMapping("/insert")
    public String displayInsertSign(InsertSignForm insertSignForm, Model model, RedirectAttributes redirectAttributes){
        if (board.isGameWin()){
            redirectAttributes.addAttribute("mark", board.getCurrentPlayer());
            return "redirect:/win";
        }
        if (board.isBoardFilled()){
            return "redirect:/gameOver";
        }
        model.addAttribute("board", board);
        return "insertSign";
    }

    @GetMapping("/insert/{dimensions}")
    public String displayInsertSign(InsertSignForm insertSignForm, Model model, RedirectAttributes redirectAttributes, @PathVariable @MatrixVariable List<String> dimensions){
        board = new Board(Integer.parseInt(dimensions.get(0)),Integer.parseInt(dimensions.get(1)));
        if (board.isGameWin()){
            redirectAttributes.addAttribute("mark", board.getCurrentPlayer());
            return "redirect:/win";
        }
        if (board.isBoardFilled()){
            return "redirect:/gameOver";
        }
        model.addAttribute("board", board);
        return "insertSign";
    }


    @PostMapping(value = "/insert")
    public String getField(@Valid InsertSignForm insertSignForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("board", board);
            return "insertSign";
        }
        board.saveForm(insertSignForm);
        return "redirect:/insert";
    }*/

}
