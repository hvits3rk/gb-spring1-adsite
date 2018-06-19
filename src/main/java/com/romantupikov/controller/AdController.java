package com.romantupikov.controller;

import com.romantupikov.ajax.AdAjax;
import com.romantupikov.entity.Ad;
import com.romantupikov.entity.Category;
import com.romantupikov.entity.Company;
import com.romantupikov.service.AdService;
import com.romantupikov.service.CategoryService;
import com.romantupikov.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ad")
public class AdController {

    private static final Logger log = LoggerFactory.getLogger(AdController.class);

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

    @GetMapping(value = "/list/ajax", produces = "application/json")
    @ResponseBody
    public AdAjax listAdAjax(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "items", defaultValue = "8") Integer items,
                             @RequestParam(value = "order", defaultValue = "DESC") String order,
                             @RequestParam(value = "orderBy", defaultValue = "publishedDate") String orderBy) {
        Sort sort = new Sort(Sort.Direction.DESC, orderBy);
        if (order.equals("ASC")) {
            sort.ascending();
        }

        PageRequest pageRequest = PageRequest.of(page, items, sort);
        Page<Ad> adPage = adService.findPaginated(pageRequest);

        AdAjax adAjax = new AdAjax();
        adAjax.setAdList(adPage.getContent());

        return adAjax;
    }

    @GetMapping("/list")
    public String adList(Model model) {
//        model.addAttribute("adList", adService.findAll());
        return "ad/list";
    }

    @GetMapping("/{id}")
    public String adDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("ad", adService.findById(id));
        return "ad/index";
    }

    @GetMapping("/add")
    public String addAd(Model model) {
        model.addAttribute("ad", new Ad());
        model.addAttribute("companyList", companyService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "ad/form";
    }

    @GetMapping("/edit/{id}")
    public String editAd(@PathVariable("id") String id, Model model) {
        model.addAttribute("ad", adService.findById(id));
        model.addAttribute("companyList", companyService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "ad/form";
    }

    @PostMapping("/save")
    public String saveAd(@ModelAttribute Ad ad,
                         @RequestParam("companyId") String companyId,
                         @RequestParam("categoryId") String categoryId) {

        Company company = companyService.findById(companyId);
        Category category = categoryService.findById(categoryId);
        ad.setCategory(category);
        ad.setCompany(company);
        adService.add(ad);
        return "redirect:list";
    }
}
