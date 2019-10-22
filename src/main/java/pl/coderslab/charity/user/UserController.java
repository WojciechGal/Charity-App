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
    public String createUser(@Valid User user, BindingResult result, Model model) {

        if(!user.getPass1().equals(user.getPass2()) || "".equals(user.getPass2())){
            FieldError err = new FieldError("user","pass2","Hasło musi zostać poprawnie powtórzone");
            result.addError(err);
        }

        if (!user.getPass1().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            FieldError err = new FieldError("user","pass1","Hasło musi zawierać co najmniej jedną wielką literę, jedną małą literę, cyfrę oraz znak specjalny");
            result.addError(err);
        }

        if (result.hasErrors()) {
            user.setPass1("");
            user.setPass2("");
            model.addAttribute("user", user);
            return "authentication/register";
        }

        user.setPassword(user.getPass1());

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
