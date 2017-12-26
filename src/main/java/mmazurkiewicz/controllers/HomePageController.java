package mmazurkiewicz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    private List<String> posts = new ArrayList<String>() {
        {
            add("Nowa Gra");
            add("Zmień domyśną wielkość planszy");
            add("Wyjdź");
        }
    };

    @RequestMapping(value="/mainMenu", params="insert", method = RequestMethod.POST)
    public String newGame(){
        return "redirect:/insert";
    }

    @RequestMapping("/")
    public String mainMenu(Model model) {
        model.addAttribute("fields",posts);
        return "menu";
    }
}
