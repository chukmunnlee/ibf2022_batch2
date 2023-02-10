package ibf2022.ssf.day11.controllers;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path={"/", "/index.html"}, produces = MediaType.TEXT_HTML_VALUE)
public class NumberController {

    private Logger logger = Logger.getLogger(NumberController.class.getName());

    // GET /, GET /index.html
    @GetMapping
    public String getIndex(Model model) {

        Random rand = new SecureRandom();
        int imgIndex = rand.nextInt(31);
        String imgUrl = "/numbers/number%d.jpg".formatted(imgIndex);

        logger.log(Level.INFO, imgUrl);

        // Bind the value to the model
        model.addAttribute("imgUrl", imgUrl);
        model.addAttribute("imgIndex", imgIndex);

        // Render the template with the model
        return "index";
    }
}
