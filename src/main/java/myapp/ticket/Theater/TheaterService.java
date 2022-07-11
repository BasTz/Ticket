package myapp.ticket.Theater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    private static final Logger logger = LoggerFactory.getLogger(TheaterService.class);
    @Autowired
    private TheaterRepository theaterRepository;
    public List<Theater> getAllTheater() {
        List<Theater> theaters = new ArrayList<>();

        theaterRepository.findAll().forEach(theaters::add);

        return theaters;
    }

    public String AddTheater(Theater theater) {
        if(theater == null) return "Save Unsuccessful Data is Null";
        else{
            theaterRepository.save(theater);
            return "Save successful";
        }
    }

    public String UpdateTheater(int id, Theater theater) {
        Theater myTheater = theaterRepository.findById(id);
        if(myTheater != null) {
            myTheater.setName(theater.getName());
            theaterRepository.save(myTheater);
            logger.info("Update : {}", myTheater);
            return "Update successful";
        }
        else return "Not Update : Data not available";
    }

    public String DeleteTheater(int id) {
        Theater myTheater = theaterRepository.findById(id);
        if(myTheater != null) {
            theaterRepository.deleteById(id);
            logger.info("Delete : Theater Id = {}", id);
            return "Delete successful";
        }
        else return "Not Delete : Data not available";
    }
}
