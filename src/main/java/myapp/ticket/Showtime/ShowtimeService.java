package myapp.ticket.Showtime;

import myapp.ticket.Customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ShowtimeService {

    private static final Logger logger = LoggerFactory.getLogger(ShowtimeService.class);
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getAllShowtime() {
        List<Showtime> showtimes = new ArrayList<>();

        showtimeRepository.findAll().forEach(showtimes::add);

        return showtimes;
    }

    public String AddShowtime(Showtime showtime) {
        if(showtime == null){
            return "!!ERROR!! Data is Null";
        }
        else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(showtime.getDatetime());
            int Hour = calendar.get(Calendar.HOUR_OF_DAY);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            if(Hour == 11 || Hour == 14 || Hour == 17 || Hour == 20) {
                List<Showtime> myShowtime = (List<Showtime>) showtimeRepository.findAll();
                if (!myShowtime.isEmpty()) {
                    for (Showtime value : myShowtime) {
                        if (showtime.getTheater().getId() == value.getTheater().getId()
                                && dateFormat.format(showtime.getDatetime()).equals(dateFormat.format(value.getDatetime()))) {
                            return "Duplicate Theater in same time";
                        }
                    }
                }
                showtimeRepository.save(showtime);
                return "Save successful";
            }
            else{
                return "Every theater has a round 11:00 14:00 17:00 20:00 only";
            }
        }
    }
    public String UpdateShowtime(int id, Showtime showtime) {
        Showtime myShowtime = showtimeRepository.findById(id);
        if(showtime == null){
            return "!!ERROR!! Data is Null";
        }
        else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(showtime.getDatetime());
            int Hour = calendar.get(Calendar.HOUR_OF_DAY);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            if(Hour == 11 || Hour == 14 || Hour == 17 || Hour == 20) {
                List<Showtime> myShowtimeTemp = (List<Showtime>) showtimeRepository.findAll();
                if (!myShowtimeTemp.isEmpty()) {
                    for (Showtime value : myShowtimeTemp) {
                        if (showtime.getTheater().getId() == value.getTheater().getId()
                                && dateFormat.format(showtime.getDatetime()).equals(dateFormat.format(value.getDatetime()))) {
                            return "Duplicate Theater in same time";
                        }
                    }
                }
                myShowtime.setMovie(showtime.getMovie());
                myShowtime.setTheater(showtime.getTheater());
                myShowtime.setDatetime(showtime.getDatetime());
                showtimeRepository.save(myShowtime);
                logger.info("Update : {}",myShowtime);
                return "Update successful";
            }
            else{
                return "Every theater has a round 11:00 14:00 17:00 20:00 only";
            }
        }
    }

    public String DeleteShowtime(int id) {
        Showtime myShowtime = showtimeRepository.findById(id);
        if(myShowtime != null) {
            showtimeRepository.deleteById(id);
            logger.info("Delete : Showtime Id = {}", id);
            return "Delete successful";
        }
        else return "Not Delete : Data not available";
    }

    public List<Showtime> ShowtimeByMovieId(int id) {
        return new ArrayList<>(showtimeRepository.findAllByMovieIdOrderByDatetime(id));
    }
}
