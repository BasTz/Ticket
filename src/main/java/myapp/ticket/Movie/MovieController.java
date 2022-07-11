package myapp.ticket.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public List<Movie> getAllMovie(){
        return movieService.getAllMovie();
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String AddMovie(@RequestBody Movie movies){
        return movieService.AddMovie(movies);
    }

    @RequestMapping(value="/movie/{id}", method = RequestMethod.PUT)
    public String UpdateMovie(@PathVariable int id, @RequestBody Movie movies) {
        return movieService.UpdateMovie(id, movies);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public String DeleteMovie(@PathVariable int id) {
        return movieService.DeleteMovie(id);
    }
}
