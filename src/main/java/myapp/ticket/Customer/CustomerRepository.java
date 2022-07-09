package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findById(int id);

    List<Customer> findAllByShowtimeIdOrderByRow(int id);
}
