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
    public void AddMovie(@RequestBody Movie movies){
        movieService.AddMovie(movies);
    }

    @RequestMapping(value="/movie/{id}", method = RequestMethod.PUT)
    public void UpdateMovie(@PathVariable int id, @RequestBody Movie movies) {
        movieService.UpdateMovie(id, movies);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public void DeleteMovie(@PathVariable int id) {
        movieService.DeleteMovie(id);
    }
}
