package myapp.ticket.Theater;

import myapp.ticket.Movie.Movie;
import myapp.ticket.Movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @RequestMapping(value = "/theater", method = RequestMethod.GET)
    public List<Theater> getAllTheater(){
        return theaterService.getAllTheater();
    }

    @RequestMapping(value = "/theater", method = RequestMethod.POST)
    public void AddTheater(@RequestBody Theater theater){
        theaterService.AddTheater(theater);
    }

    @RequestMapping(value="/theater/{id}", method = RequestMethod.PUT)
    public void UpdateTheater(@PathVariable int id, @RequestBody Theater theater) {
        theaterService.UpdateTheater(id, theater);
    }

    @RequestMapping(value = "/theater/{id}", method = RequestMethod.DELETE)
    public void DeleteTheater(@PathVariable int id) {
        theaterService.DeleteTheater(id);
    }
}
