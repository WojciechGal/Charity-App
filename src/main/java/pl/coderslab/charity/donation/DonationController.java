package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import java.util.List;

@Controller
public class DonationController {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/form")
    public String formAction(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));

        Donation donation = new Donation();
        model.addAttribute("donation", donation);

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "form";
    }

    @PostMapping("/form")
    public String formAction() {

        return "unknown";
    }
}
