package myapp.ticket.Showtime;

import myapp.ticket.Theater.Theater;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ShowtimeRepository extends CrudRepository<Showtime,Integer> {
    Showtime findById(int id);
    Showtime findByDatetime(Date date);
    List<Showtime> findAllByMovieIdOrderByDatetime(int id);
}
