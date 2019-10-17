package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.role.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/users")
@SessionAttributes("roles")
public class AdditionalUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/updateRest")
    public String updateRest(@AuthenticationPrincipal CurrentUser loggedUser, Model model) {
        User user = loggedUser.getUser();

        Set<Role> roles = user.getRoles();

        model.addAttribute("roles", roles);

        model.addAttribute("user", user);
        return "authentication/updateRest";
    }

    @PostMapping("/updateRest")
    public String updateRest(HttpSession session, @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "authentication/updateRest";
        }

        Set<Role> roles = (Set<Role>) session.getAttribute("roles");

        user.setRoles(roles);

        userService.saveUserSimpleVersion(user);

        return "redirect:/";
    }

    @GetMapping("/updatePass")
    public String updatePass(@AuthenticationPrincipal CurrentUser loggedUser, Model model) {
        User user = loggedUser.getUser();
        user.setPassword("");

        Set<Role> roles = user.getRoles();

        model.addAttribute("roles", roles);

        model.addAttribute("user", user);
        return "authentication/updatePass";
    }

    @PostMapping("/updatePass")
    public String updatePass(HttpSession session, @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "authentication/updatePass";
        }

        Set<Role> roles = (Set<Role>) session.getAttribute("roles");

        user.setRoles(roles);

        userService.saveUserSimpleVersionWithPasswordHash(user);

        return "redirect:/";
    }
}
