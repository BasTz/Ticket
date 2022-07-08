package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public void AddCustomer(@RequestBody Customer customer){
        customerService.AddCustomer(customer);
    }
}
