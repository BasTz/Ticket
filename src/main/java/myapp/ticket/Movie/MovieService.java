package myapp.ticket.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();

        movieRepository.findAll().forEach(movies::add);

        return movies;
    }



    public void AddMovie(Movie movies) {
        movieRepository.save(movies);
    }
}
