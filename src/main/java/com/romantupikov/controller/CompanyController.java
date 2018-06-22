package com.romantupikov.controller;

import com.romantupikov.entity.Company;
import com.romantupikov.service.AdService;
import com.romantupikov.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final AdService adService;

    public CompanyController(CompanyService companyService, AdService adService) {
        this.companyService = companyService;
        this.adService = adService;
    }

    @ModelAttribute("activeController")
    public String activeController() {
        return "company";
    }

    @GetMapping("/list")
    public String companyList(Model model) {
        model.addAttribute("companyList", companyService.findAll());
        return "company/list";
    }

    @GetMapping("/detail/{id}")
    public String companyDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("company", companyService.findById(id))
                .addAttribute("adList", adService.findByCompanyId(id));
        return "company/index";
    }

    @GetMapping("/add")
    public String companyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/form";
    }

    @PostMapping("/save")
    public String addCompany(@ModelAttribute Company company) {
        companyService.add(company);
        return "redirect:list";
    }
}
