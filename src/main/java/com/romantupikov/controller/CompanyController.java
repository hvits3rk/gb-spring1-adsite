package com.romantupikov.controller;

import com.romantupikov.entity.Company;
import com.romantupikov.service.AdService;
import com.romantupikov.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String companyForm(final Company company) {
        return "company/form";
    }

    @PostMapping("/add")
    public String addCompany(@Valid final Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company/form";
        }

        companyService.add(company);
        return "redirect:list";
    }
}
