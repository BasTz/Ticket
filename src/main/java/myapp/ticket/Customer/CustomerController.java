package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import myapp.ticket.Showtime.Showtime;
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
    public String AddCustomer(@RequestBody Customer customer){
        return customerService.AddCustomer(customer);
    }

    @RequestMapping(value="/customer/{id}", method = RequestMethod.PUT)
    public String UpdateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerService.UpdateCustomer(id, customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public String DeleteCustomer(@PathVariable int id) {
        return customerService.DeleteCustomer(id);
    }

    @RequestMapping(value = "/customerbyshowtime/{id}", method = RequestMethod.GET)
    public List<Customer> CustomerByShowtimeId(@PathVariable int id){
        return customerService.CustomerByShowtimeId(id);
    }
}
