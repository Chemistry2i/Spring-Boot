package com.sms.Server.controller;

import com.sms.Server.entity.Role;
import com.sms.Server.entity.School;
import com.sms.Server.entity.User;
import com.sms.Server.service.SchoolService;
import com.sms.Server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final SchoolService schoolService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        // Get user roles
        Set<String> roles = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toSet());

        // Get school count
        List<School> schools = schoolService.getAllSchools();

        model.addAttribute("username", username);
        model.addAttribute("roles", String.join(", ", roles));
        model.addAttribute("userRole", roles.iterator().next()); // Primary role
        model.addAttribute("schoolCount", schools.size());
        model.addAttribute("userCount", 2); // Default users
        model.addAttribute("schools", schools);

        return "dashboard";
    }

    @GetMapping("/schools/new")
    public String newSchoolForm() {
        return "school-form";
    }

    @PostMapping("/schools")
    public String createSchool(@ModelAttribute School school,
                              RedirectAttributes redirectAttributes,
                              Authentication authentication) {

        // Check if user has permission (SUPERADMIN or ADMIN)
        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERADMIN") ||
                                auth.getAuthority().equals("ROLE_ADMIN"));

        if (!hasPermission) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to register schools");
            return "redirect:/dashboard";
        }

        try {
            schoolService.createSchool(school);
            redirectAttributes.addFlashAttribute("success", "School registered successfully!");
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/schools/new";
        }
    }

    @GetMapping("/schools/{id}/edit")
    public String editSchoolForm(@PathVariable Long id, Model model, Authentication authentication) {
        // Check permissions
        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERADMIN"));

        if (!hasPermission) {
            return "redirect:/dashboard";
        }

        schoolService.getSchoolById(id).ifPresent(school -> model.addAttribute("school", school));
        return "school-form";
    }

    @PostMapping("/schools/{id}")
    public String updateSchool(@PathVariable Long id, @ModelAttribute School school, RedirectAttributes redirectAttributes, Authentication authentication) {
        // Check permissions
        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERADMIN"));

        if (!hasPermission) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to update schools");
            return "redirect:/dashboard";
        }

        try {
            schoolService.updateSchool(id, school);
            redirectAttributes.addFlashAttribute("success", "School updated successfully!");
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update school: " + e.getMessage());
            return "redirect:/schools/" + id + "/edit";
        }
    }

    @PostMapping("/schools/{id}/delete")
    public String deleteSchool(@PathVariable Long id, RedirectAttributes redirectAttributes, Authentication authentication) {
        // Check permissions
        boolean hasPermission = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERADMIN"));

        if (!hasPermission) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to delete schools");
            return "redirect:/dashboard";
        }

        try {
            schoolService.deleteSchool(id);
            redirectAttributes.addFlashAttribute("success", "School deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(javax.servlet.http.HttpServletResponse response) {
        // Clear the token cookie
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }
}