package pl.coderslab.charity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAdminsController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admins")
    public String adminAdmins(Model model) {

        List<User> admins = userService.findUsersWhoAreAdmins();
        model.addAttribute("admins", admins);

        return "admin-sb-2/checkAdmins";
    }

    @GetMapping("/addAdmin")
    public String addAdmin(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "admin-sb-2/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@Valid User user, BindingResult result, Model model) {

        if (!user.getPass1().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            FieldError err = new FieldError("user","pass1","Hasło musi zawierać co najmniej jedną wielką literę, jedną małą literę, cyfrę oraz znak specjalny");
            result.addError(err);
        }

        if(!user.getPass1().equals(user.getPass2()) || "".equals(user.getPass2())){
            FieldError err = new FieldError("user","pass2","Hasło musi zostać poprawnie powtórzone");
            result.addError(err);
        }

        if (result.hasErrors()) {
            user.setPass1("");
            user.setPass2("");
            model.addAttribute("user", user);
            return "admin-sb-2/addAdmin";
        }

        user.setPassword(user.getPass1());

        userService.saveAdmin(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/updateAdmin/{id}")
    public String updateAdmin(Model model, @PathVariable Long id) {

        User user = userService.findUserById(id);
        model.addAttribute("user", user);

        return "admin-sb-2/updateAdmin";
    }

    @PostMapping("/updateAdmin/{id}")
    public String updateAdmin(@Valid User user, BindingResult result, Model model) {

        if (!user.getPass1().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            FieldError err = new FieldError("user","pass1","Hasło musi zawierać co najmniej jedną wielką literę, jedną małą literę, cyfrę oraz znak specjalny");
            result.addError(err);
        }

        if (result.hasErrors()) {
            user.setPass1("");
            model.addAttribute("user", user);
            return "admin-sb-2/updateAdmin";
        }

        user.setPassword(user.getPass1());

        userService.saveAdmin(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id, @AuthenticationPrincipal CurrentUser loggedUser) {

        Long idLogged = loggedUser.getUser().getId();

        if (idLogged.equals(id)) {
            return "redirect:/admin/deleteMyself";
        }

        userService.deleteUserById(id);

        return "redirect:/admin/admins";
    }
}