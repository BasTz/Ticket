package myapp.ticket.Customer;

import myapp.ticket.Example;
import myapp.ticket.Movie.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(Example.class);
    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public void AddCustomer(Customer customer) {
        customerRepository.save(customer);
        Customer myCustomer = customerRepository.findById(customer.getId());
        logger.info("Booking : {}",myCustomer);
    }

    public void UpdateCustomer(int id, Customer customer) {
        Customer myCustomer = customerRepository.findById(id);
        myCustomer.setRow(customer.getRow());
        myCustomer.setCol(customer.getCol());
        myCustomer.setShowtime(customer.getShowtime());
        customerRepository.save(myCustomer);
        logger.info("Update : {}",myCustomer);
    }

    public void DeleteCustomer(int id) {
        Customer myCustomer = customerRepository.findById(id);
        customerRepository.deleteById(id);
        logger.info("Cancel Booking : {}",myCustomer);
    }
}
