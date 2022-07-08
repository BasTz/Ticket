package myapp.ticket.Showtime;

import myapp.ticket.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.List;

@RestController
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @RequestMapping(value = "/showtime", method = RequestMethod.GET)
    public List<Showtime> getAllShowtime(){
        return showtimeService.getAllShowtime();
    }

    @RequestMapping(value = "/showtime", method = RequestMethod.POST)
    public void AddShowtime(@RequestBody Showtime showtime){
        showtimeService.AddShowtime(showtime);
    }
}
