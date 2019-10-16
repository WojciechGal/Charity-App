package pl.coderslab.charity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addAdmin(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "admin-sb-2/addAdmin";
        }

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
    public String updateAdmin(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "admin-sb-2/updateAdmin";
        }

        userService.saveAdmin(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String addInstitution(@PathVariable Long id) {

        userService.deleteUserById(id);

        return "redirect:/admin/admins";
    }
}