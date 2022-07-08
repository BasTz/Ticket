package myapp.ticket.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
