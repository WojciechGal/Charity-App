package pl.coderslab.charity.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    public EmailServiceImpl emailService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String sendMessage(@RequestParam String name, @RequestParam String surname,
                              @RequestParam String message, @RequestParam String email) {

        if ("".equals(name) || "".equals(surname) || "".equals(email) || "".equals(message)) {
            return "redirect:/messageNotSend";
        }

        emailService.sendSimpleMessage("charity.app.121@gmail.com",
                "Wiadomość od klienta", name + " " + surname + " " + email + ": " + message);

        return "redirect:/messageSend";
    }
}
