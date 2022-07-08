package myapp.ticket.Showtime;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtime() {
        List<Showtime> showtimes = new ArrayList<>();

        showtimeRepository.findAll().forEach(showtimes::add);

        return showtimes;
    }

    public void AddShowtime(Showtime showtime) {
        showtimeRepository.save(showtime);
    }
}
