package com.romantupikov.controller;

import com.romantupikov.entity.Ad;
import com.romantupikov.entity.Category;
import com.romantupikov.entity.Company;
import com.romantupikov.pagination.AdPaginationModel;
import com.romantupikov.service.AdService;
import com.romantupikov.service.CategoryService;
import com.romantupikov.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;
    private final CompanyService companyService;
    private final CategoryService categoryService;

    public AdController(AdService adService, CompanyService companyService, CategoryService categoryService) {
        this.adService = adService;
        this.companyService = companyService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("activeController")
    public String activeController() {
        return "ad";
    }

    @GetMapping(value = "/list/pagination", produces = "application/json")
    @ResponseBody
    public AdPaginationModel listPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "items", defaultValue = "8") Integer items,
            @RequestParam(value = "order", defaultValue = "DESC") String order,
            @RequestParam(value = "orderBy", defaultValue = "publishedDate") String orderBy) {

        return adService.getAdPage(page, items, order, orderBy);
    }

    @GetMapping("/list")
    public String adList(Model model) {
        // pre-render first page
        model.addAttribute("adList",
                adService.getAdPage(0, 8, "DESC", "publishedDate").getAdList());
        return "ad/list";
    }

    @GetMapping("/detail/{id}")
    public String adDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("ad", adService.findById(id));
        return "ad/index";
    }

    @GetMapping("/add")
    public String addAd(final Ad ad, Model model) {
        model.addAttribute("companyList", companyService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "ad/form";
    }

    @PostMapping("/add")
    public String saveAd(@Valid final Ad ad, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("companyList", companyService.findAll());
            model.addAttribute("categoryList", categoryService.findAll());
            return "ad/form";
        }

        Company company = companyService.findById(ad.getCompany().getId());
        Category category = categoryService.findById(ad.getCategory().getId());
        ad.setCategory(category);
        ad.setCompany(company);
        adService.add(ad);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editAd(@PathVariable("id") String id, Model model) {
        model.addAttribute("ad", adService.findById(id));
        model.addAttribute("companyList", companyService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "ad/form";
    }
}
