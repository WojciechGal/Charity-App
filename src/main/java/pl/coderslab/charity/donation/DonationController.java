package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
public class DonationController {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/form")
    public String formAction(@AuthenticationPrincipal CurrentUser loggedUser, Model model) {
        User user = loggedUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));

        setCategoriesAndInstitutionsAndDonation(model);

        return "form";
    }

    @PostMapping("/form")
    public String formAction(Model model, @Valid Donation donation, BindingResult result,
                             @AuthenticationPrincipal CurrentUser loggedUser) {

        User user = loggedUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (result.hasErrors()) {
            return "form";
        }

        donation.setUser(loggedUser.getUser());

        donationService.saveDonation(donation);

        return "redirect:/";
    }

    @GetMapping("/unloggedForm")
    public String unloggedFormAction(Model model) {

        setCategoriesAndInstitutionsAndDonation(model);

        return "unloggedForm";
    }

    @PostMapping("/unloggedForm")
    public String unloggedFormAction(@Valid Donation donation, BindingResult result, Model model) {

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        if (result.hasErrors()) {
            return "unloggedForm";
        }

        donationService.saveDonation(donation);

        return "redirect:/";
    }

    @GetMapping("/myDonations")
    public String myDonations(@AuthenticationPrincipal CurrentUser loggedUser, Model model){
        User user = loggedUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));

        List<Donation> donations = donationService.findDonationsByUser(user);

        donations.sort(Comparator.comparingInt(Donation::getStatus)
                .thenComparing(Donation::getReceivedOn, Comparator.nullsFirst(Comparator.naturalOrder()))
                .thenComparing(Donation::getCreatedOn, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        model.addAttribute("donations", donations);

        return "myDonations";
    }

    @GetMapping("/donationDetails/{id}")
    public String getDonationDetails(@AuthenticationPrincipal CurrentUser loggedUser, @PathVariable Long id, Model model) {
        User user = loggedUser.getUser();
        model.addAttribute("user", userService.findByUserEmail(user.getEmail()));
        model.addAttribute("donation", donationService.findDonationById(id));
        return "/donationDetails";
    }

    @GetMapping("/donationReceived/{id}")
    public String donationReceived(@PathVariable Long id) {
        Donation donation = donationService.findDonationById(id);
        donation.setStatus(1);
        donationService.saveDonation(donation);
        return "redirect:/donationDetails/" + id;
    }

    ////////////////////////////////////////////////////////////////////

    private void setCategoriesAndInstitutionsAndDonation(Model model) {

        Donation donation = new Donation();
        donation.setStatus(0);
        model.addAttribute("donation", donation);

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

}
