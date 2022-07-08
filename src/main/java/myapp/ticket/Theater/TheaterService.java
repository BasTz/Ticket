package myapp.ticket.Theater;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    public List<Theater> getAllTheater() {
        List<Theater> theaters = new ArrayList<>();

        theaterRepository.findAll().forEach(theaters::add);

        return theaters;
    }

    public void AddTheater(Theater theater) {
        theaterRepository.save(theater);
    }
}
