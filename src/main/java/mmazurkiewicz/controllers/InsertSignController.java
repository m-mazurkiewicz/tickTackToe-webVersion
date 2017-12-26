package mmazurkiewicz.controllers;

import mmazurkiewicz.forms.InsertSignForm;
import mmazurkiewicz.models.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class InsertSignController {
    private Board board;

    @Autowired
    public InsertSignController(Board board){
        this.board = board;
    }

    @RequestMapping("/insert")
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

    @PostMapping(value = "/insert")
    public String getField(InsertSignForm insertSignForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "insertSign";
        }
        board.saveForm(insertSignForm);
        return "redirect:/insert";
    }

}
