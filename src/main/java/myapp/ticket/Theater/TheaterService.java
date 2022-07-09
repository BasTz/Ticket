package myapp.ticket.Theater;

import myapp.ticket.Example;
import myapp.ticket.Movie.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    private static final Logger logger = LoggerFactory.getLogger(Example.class);
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

    public void UpdateTheater(int id, Theater theater) {
        Theater myTheater = theaterRepository.findById(id);
        myTheater.setName(theater.getName());
        theaterRepository.save(myTheater);
        logger.info("Update : {}",myTheater);
    }

    public void DeleteTheater(int id) {
        theaterRepository.deleteById(id);
        logger.info("Delete : Theater Id = {}",id);
    }
}
