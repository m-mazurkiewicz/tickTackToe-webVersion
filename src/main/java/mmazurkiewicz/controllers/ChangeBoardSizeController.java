package mmazurkiewicz.controllers;

import mmazurkiewicz.forms.ChangeBoardSizeForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class ChangeBoardSizeController {

    @GetMapping("/changeBoardSize")
    public String displayPage(ChangeBoardSizeForm changeBoardSizeForm){
        return "changeBoardSize";
    }

    @PostMapping("/changeBoardSize")
    public String getNewSize(@Valid ChangeBoardSizeForm changeBoardSizeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "changeBoardSize";
        }
        return "redirect:/insert/"+changeBoardSizeForm.getX()+","+changeBoardSizeForm.getY();
    }

}

