package pl.coderslab.charity.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminDashboardController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping(path = "/dashboard")
    public String adminDashboard() {

        return "admin-sb-2/index";
    }

    @GetMapping(path = "/institutions")
    public String adminInstitutions(Model model) {

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        return "admin-sb-2/checkInstitutions";
    }
}
