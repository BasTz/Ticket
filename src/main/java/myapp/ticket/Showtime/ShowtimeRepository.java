package myapp.ticket.Showtime;

import myapp.ticket.Theater.Theater;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ShowtimeRepository extends CrudRepository<Showtime,Integer> {
    Showtime findById(int id);
    Showtime findByDatetime(Date date);
    Showtime findByMovie(int id);
}
