package myapp.ticket.Theater;

import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends CrudRepository<Theater,Integer> {
    Theater findById(int id);
}
