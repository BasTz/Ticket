package myapp.ticket.Showtime;

import com.fasterxml.jackson.annotation.JsonFormat;
import myapp.ticket.Example;
import myapp.ticket.Movie.Movie;
import myapp.ticket.Theater.Theater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowtimeService {

    private static final Logger logger = LoggerFactory.getLogger(Example.class);
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtime() {
        List<Showtime> showtimes = new ArrayList<>();

        showtimeRepository.findAll().forEach(showtimes::add);

        return showtimes;
    }

    public void AddShowtime(Showtime showtime) {
        boolean Flag = true;
        List<Showtime> myShowtime = (List<Showtime>) showtimeRepository.findAll();
        if(!myShowtime.isEmpty()){
            for(int i = 0;i<myShowtime.size();i++){
                if(showtime.getMovie().getId() == myShowtime.get(i).getMovie().getId() && showtime.getTheater().getId() == myShowtime.get(i).getTheater().getId()
                        && showtime.getDatetime().compareTo(myShowtime.get(i).getDatetime()) == 0){
                }
            }
        }

        if(Flag){
            showtimeRepository.save(showtime);
        }
    }
    public void UpdateShowtime(int id, Showtime showtime) {
        Showtime myShowtime = showtimeRepository.findById(id);
        myShowtime.setMovie(showtime.getMovie());
        myShowtime.setTheater(showtime.getTheater());
        myShowtime.setDatetime(showtime.getDatetime());
        showtimeRepository.save(myShowtime);
        logger.info("Update : {}",myShowtime);
    }

    public void DeleteShowtime(int id) {
        showtimeRepository.deleteById(id);
        logger.info("Delete : Showtime Id = {}",id);
    }
}
