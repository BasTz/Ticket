package myapp.ticket.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
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

    public void UpdateMovie(int id, Movie movies) {
        Movie myMovies = movieRepository.findById(id);
        myMovies.setName(movies.getName());
        movieRepository.save(myMovies);
        logger.info("Update : {}",myMovies);
    }

    public void DeleteMovie(int id) {
        movieRepository.deleteById(id);
        logger.info("Delete : Movie Id = {}",id);
    }
}
