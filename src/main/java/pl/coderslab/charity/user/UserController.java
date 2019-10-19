package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "authentication/register";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult result) {

        if(!user.getPassword().equals(user.getPassword2())){
            FieldError err = new FieldError("user","password2","Hasło musi zostać poprawnie powtórzone");
            result.addError(err);
        }

        if (result.hasErrors()) {
            return "authentication/register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/check")
    @ResponseBody
    public String check(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "You are " + entityUser.getEmail() + ", and you are authenticated as " + entityUser.getRoles();
    }

}
