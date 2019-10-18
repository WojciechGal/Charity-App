package pl.coderslab.charity.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.home.HomeController;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
public class MessageNotSendController {

    @Autowired
    InstitutionService institutionService;

    @Autowired
    DonationService donationService;

    @GetMapping("/messageNotSend")
    public String messageNotSend(Model model) {

        HomeController.extractionOfModelDonationAndInstitution(model, institutionService, donationService);

        return "messageNotSend";
    }
}
