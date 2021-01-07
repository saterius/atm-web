package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // responsible for handle user request
    //  step 1. update model for template
    //  step 2. choose HTML template

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // handle user request
    @GetMapping
    public String getCustomerPage(Model model) {

        //  step 1. update model for template
        model.addAttribute("allCustomers", customerService.getCustomers());

        //  step 2. choose HTML template
        return "customer";  // customer.html template
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("allCustomers", customerService.getCustomers());
        return "redirect:customer";
    }
}
