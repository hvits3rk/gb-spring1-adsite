package com.romantupikov.controller;

import com.romantupikov.entity.Category;
import com.romantupikov.service.AdService;
import com.romantupikov.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final AdService adService;

    public CategoryController(CategoryService categoryService, AdService adService) {
        this.categoryService = categoryService;
        this.adService = adService;
    }

    @ModelAttribute("activeController")
    public String activeController() {
        return "category";
    }

    @GetMapping("/list")
    public String categoryList(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/detail/{id}")
    public String categoryDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("category", categoryService.findById(id))
                .addAttribute("adList", adService.findByCategoryId(id));
        return "category/index";
    }

    @GetMapping("/add")
    public String categoryForm(final Category category) {
        return "category/form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid final Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "category/form";
        }

        categoryService.add(category);
        return "redirect:list";
    }

}
