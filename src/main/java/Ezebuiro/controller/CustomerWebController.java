package Ezebuiro.controller;

import Ezebuiro.Entities.Customer;
import Ezebuiro.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable int id, Model model) {
        try {
            Customer customer = customerService.getCustomer(id);
            model.addAttribute("customer", customer);
            return "customer/details";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer()); //show blank canvas
        return "customer/add";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addOrUpdateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            Customer customer = customerService.getCustomer(id);
            model.addAttribute("customer", customer); //show pre-filled canvas
            return "customer/edit";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.addOrUpdateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
