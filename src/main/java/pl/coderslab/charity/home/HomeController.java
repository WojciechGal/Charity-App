package pl.coderslab.charity.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    InstitutionService institutionService;

    @RequestMapping("/")
    public String homeAction(Model model){

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        Long supportedInstitutions = institutionService.checkNumberOfSupportedInstitutions();
        model.addAttribute("suppInst", supportedInstitutions);

        Long allGivenQuantities = institutionService.checkNumberOfSupportedInstitutions();
        model.addAttribute("allQuant", allGivenQuantities);

        return "index";
    }
}
