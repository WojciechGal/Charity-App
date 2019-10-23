package pl.coderslab.charity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUsersController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public String adminUsers(Model model) {

        List<User> users = userService.findUsersWhoAreUsers();
        model.addAttribute("users", users);

        return "admin-sb-2/checkUsers";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(Model model, @PathVariable Long id) {

        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "admin-sb-2/updateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@Valid User user, BindingResult result, Model model) {

        if (!user.getPass1().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            FieldError err = new FieldError("user","pass1","Hasło musi zawierać co najmniej jedną wielką literę, jedną małą literę, cyfrę oraz znak specjalny");
            result.addError(err);
        }

        if (result.hasErrors()) {
            user.setPass1("");
            model.addAttribute("user", user);
            return "admin-sb-2/updateUser";
        }

        user.setPassword(user.getPass1());

        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUserById(id);

        return "redirect:/admin/users";
    }

    @GetMapping("/blockUser/{id}")
    public String blockUser(@PathVariable Long id) {

        User user = userService.findUserById(id);
        userService.blockUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/unblockUser/{id}")
    public String unblockUser(@PathVariable Long id) {

        User user = userService.findUserById(id);
        userService.unblockUser(user);

        return "redirect:/admin/users";
    }
}
