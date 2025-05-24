package Ezebuiro.controller;
import Ezebuiro.Entities.Boat;
import Ezebuiro.Services.BoatService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/boats")
public class BoatWebController {

    @Autowired
    private BoatService boatService;

    @GetMapping
    public String listBoats(Model model) {
        List<Boat> boats = boatService.getAllBoats();
        model.addAttribute("boats", boats);
        return "boat/list";
    }

    @GetMapping("/{id}")
    public String showBoatDetails(@PathVariable int id, Model model) {
        try {
            Boat boat = boatService.getBoatById(id);
            model.addAttribute("boat", boat);
            return "boat/details";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "boat/error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("boat", new Boat()); // empty boat for the form
        return "boat/add";
    }


    @PostMapping("/add")
    public String addBoat(@ModelAttribute Boat boat) {
        boatService.addOrUpdateBoat(boat);
        return "redirect:/boats"; // return to the list of boats
    }

    @GetMapping("/filter")
    public String showFilterForm(Model model) {
        model.addAttribute("boat", new Boat());
        model.addAttribute("boats", boatService.getAllBoats());
        return "boat/filter";
    }



    @PostMapping("/filter")
    public String filter(@RequestParam(required = false) Integer min,
                         @RequestParam(required = false) Integer max,
                         @RequestParam(required = false) Double price,
                         @RequestParam(required = false) String brand,
                         @RequestParam(required = false) String boatModel,
                         Model model) {
        List<Boat> boats = boatService.advancedsearch(
                min != null ? min : 0,
                max != null ? max : Integer.MAX_VALUE,
                price != null ? price : Double.MAX_VALUE,
                brand != null ? brand : "",
                boatModel != null ? boatModel : ""
        );
        model.addAttribute("boats", boats);
        return "boat/filteredBoats";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            Boat boat = boatService.getBoatById(id);
            model.addAttribute("boat", boat);
            return "boat/edit"; // name of the  Thymeleaf template
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "boat/error"; // error page
        }
    }

    @PostMapping("/edit")
    public String updateBoat(@ModelAttribute Boat boat) {
        boatService.addOrUpdateBoat(boat);
        return "redirect:/boats"; // return to the list of boats
    }

    @GetMapping("/delete/{id}")
    public String deleteBoat(@PathVariable int id) {
        boatService.delete(id); // completes in one step, no delete page
        return "redirect:/boats"; // return to the list of boats
    }
}
