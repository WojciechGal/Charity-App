package pl.coderslab.charity.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminDashboardController {

    @GetMapping(path = "/dashboard")
    public String adminDashboard() {

        return "admin-sb-2/index";
    }
}
