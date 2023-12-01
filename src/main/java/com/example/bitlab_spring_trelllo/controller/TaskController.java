package com.example.bitlab_spring_trelllo.controller;
import com.example.bitlab_spring_trelllo.service.TaskService;
import com.example.bitlab_spring_trelllo.dto.TaskCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "task-details";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String create(TaskCreateEditDto task) {
        taskService.create(task);
        return "redirect:/folders/" + task.getFolderId();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         TaskCreateEditDto task) {
        taskService.update(id, task);
        return "redirect:/tasks/"+id;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return "redirect:/";
    }
}
