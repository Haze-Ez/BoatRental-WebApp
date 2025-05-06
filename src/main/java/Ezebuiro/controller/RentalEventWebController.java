package Ezebuiro.controller;

import Ezebuiro.Entities.RentalEvent;
import Ezebuiro.Services.RentalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rentals")
public class RentalEventWebController {

    @Autowired
    private RentalEventService rentalEventService;

    @GetMapping
    public String listRentalEvents(Model model) {
        List<RentalEvent> rentalEvents = rentalEventService.getAllRentalEvents();
        model.addAttribute("rentalEvents", rentalEvents);
        return "rental-events/list"; // Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String showRentalEventDetails(@PathVariable int id, Model model) {
        try {
            RentalEvent rentalEvent = rentalEventService.getRentalEventById(id);
            model.addAttribute("rentalEvent", rentalEvent);
            return "rental-events/details"; // Thymeleaf template name
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rentalEvent", new RentalEvent());
        return "rental-events/add"; // Thymeleaf template name
    }

    @PostMapping("/add")
    public String addRentalEvent(@ModelAttribute RentalEvent rentalEvent) {
        rentalEventService.Rentboat(rentalEvent);
        return "redirect:/rentals";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            RentalEvent rentalEvent = rentalEventService.getRentalEventById(id);
            model.addAttribute("rentalEvent", rentalEvent);
            return "rental-events/edit"; // Thymeleaf template name
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String updateRentalEvent(@ModelAttribute RentalEvent rentalEvent) {
        rentalEventService.UpdateEvent(rentalEvent);
        return "redirect:/rentals";
    }

    @GetMapping("/delete/{id}")
    public String deleteRentalEvent(@PathVariable int id) {
        rentalEventService.deleteRentalEvent(id);
        return "redirect:/rentals";
    }

    @GetMapping("/return/{id}")
    public String CloseEvent(@PathVariable int id) {
        rentalEventService.ReturnBoat(id);
        return "redirect:/rentals";
    }
}
