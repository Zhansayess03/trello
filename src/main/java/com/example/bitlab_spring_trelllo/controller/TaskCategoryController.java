package com.example.bitlab_spring_trelllo.controller;
import com.example.bitlab_spring_trelllo.dto.TaskCategoryCreateEditDto;
import com.example.bitlab_spring_trelllo.service.TaskCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class TaskCategoryController {

    private final TaskCategoryService taskCategoryService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categories", taskCategoryService.findAll());
        return "categories";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("category", taskCategoryService.findById(id));
        return "category-details";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String create(TaskCategoryCreateEditDto taskCategory) {
        taskCategoryService.create(taskCategory);
        return "redirect:/categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         TaskCategoryCreateEditDto taskCategory) {
        taskCategoryService.update(id, taskCategory);
        return "redirect:/categories/" + id;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        taskCategoryService.delete(id);
        return "redirect:/";
    }
}
