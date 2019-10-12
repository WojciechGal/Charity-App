package pl.coderslab.charity.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {

        List<Donation> donations = donationService.getAllDonations();

        model.addAttribute("donations", donations);

        return "adminDashboard";
    }
}
