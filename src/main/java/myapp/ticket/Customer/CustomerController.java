package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/customer/{id}", method = RequestMethod.PUT)
    public void UpdateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customerService.UpdateCustomer(id, customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void DeleteCustomer(@PathVariable int id) {
        customerService.DeleteCustomer(id);
    }
}
