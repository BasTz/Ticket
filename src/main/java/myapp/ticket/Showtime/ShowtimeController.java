package myapp.ticket.Showtime;

import myapp.ticket.Movie.Movie;
import myapp.ticket.Theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/showtime/{id}", method = RequestMethod.PUT)
    public void UpdateShowtime(@PathVariable int id, @RequestBody Showtime showtime) {
        showtimeService.UpdateShowtime(id, showtime);
    }

    @RequestMapping(value = "/showtime/{id}", method = RequestMethod.DELETE)
    public void DeleteShowtime(@PathVariable int id) {
        showtimeService.DeleteShowtime(id);
    }
}
