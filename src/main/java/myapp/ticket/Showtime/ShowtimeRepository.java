package myapp.ticket.Showtime;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowtimeRepository extends CrudRepository<Showtime,Integer> {
    Showtime findById(int id);

    List<Showtime> findAllByMovieIdOrderByDatetime(int id);
}
