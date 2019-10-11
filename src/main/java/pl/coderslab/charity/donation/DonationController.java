package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Controller
public class DonationController {

    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String formAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));
        return "form";
    }

    @PostMapping("/form")
    public String formAction() {

        return "unknown";
    }
}
