package ibf2022.ssf.day11.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Tell Spring Boot this is the controller
@Controller
@RequestMapping(path={"/time.html", "/time"})
public class TimeController {

    // GET /time
    @GetMapping
    public String getTime(Model model) {

        Date date = new Date();
        String currentTime = date.toString();

        System.out.printf(">> the curent time is %s\n", currentTime);

        // Add the time to model
        model.addAttribute("time", currentTime);

        // return the view
        return "time";
    }
}
