package pl.coderslab.charity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminInstitutionsController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/institutions")
    public String adminInstitutions(Model model) {

        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);

        return "admin-sb-2/checkInstitutions";
    }

    @GetMapping("/addInstitution")
    public String addInstitution(Model model) {

        Institution institution = new Institution();
        model.addAttribute("institution", institution);

        return "admin-sb-2/addInstitution";
    }

    @PostMapping("/addInstitution")
    public String addInstitution(@Valid Institution institution, BindingResult result) {

        if (result.hasErrors()) {
            return "admin-sb-2/addInstitution";
        }

        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions";
    }
}
