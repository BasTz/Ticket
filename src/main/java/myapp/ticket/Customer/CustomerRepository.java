package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findById(int id);
}
