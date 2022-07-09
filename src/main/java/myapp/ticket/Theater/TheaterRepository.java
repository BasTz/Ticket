package myapp.ticket.Theater;

import myapp.ticket.Movie.Movie;
import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends CrudRepository<Theater,Integer> {
    Theater findById(int id);
}
