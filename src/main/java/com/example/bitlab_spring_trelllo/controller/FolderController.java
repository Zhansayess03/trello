package com.example.bitlab_spring_trelllo.controller;
import com.example.bitlab_spring_trelllo.dto.FolderCreateEditDto;
import com.example.bitlab_spring_trelllo.service.FolderService;
import com.example.bitlab_spring_trelllo.service.TaskCategoryService;
import com.example.bitlab_spring_trelllo.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class FolderController {

    private final FolderService folderService;
    private final TaskService taskService;
    private final TaskCategoryService taskCategoryService;


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("folders", folderService.findAll());
        return "index";
    }

    @GetMapping("/folders/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("folder", folderService.findById(id));
        model.addAttribute("tasks", taskService.findByFolderId(id));
        model.addAttribute("allCategories", taskCategoryService.findAll());
        return "folder-details";
    }

    @PostMapping("/folders/create")
    public String create(FolderCreateEditDto folder) {
        folderService.create(folder);
        return "redirect:/";
    }

    @PostMapping("/folders/update/{id}")
    public String update(@PathVariable("id") Long id,
                         FolderCreateEditDto folder) {
        folderService.update(id, folder);
        return "redirect:/";
    }

    @PostMapping("/folders/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        folderService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/folders/assign-category")
    public String assignCategory(@RequestParam("folder-id") Long folderId,
                                 @RequestParam("category-id") Long categoryId) {
        folderService.assignCategory(folderId, categoryId);
        return "redirect:/folders/"+folderId;
    }

    @PostMapping("/folders/unassign-category")
    public String unassignCategory(@RequestParam("folder-id") Long folderId,
                                   @RequestParam("category-id") Long categoryId) {
        folderService.unassignCategory(folderId, categoryId);
        return "redirect:/folders/"+folderId;
    }
}
