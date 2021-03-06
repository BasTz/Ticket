package myapp.ticket.Movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findById(int id);
}
