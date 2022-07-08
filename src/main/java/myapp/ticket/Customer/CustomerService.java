package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public void AddCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
