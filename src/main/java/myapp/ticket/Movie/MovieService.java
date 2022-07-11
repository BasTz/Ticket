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



    public String AddMovie(Movie movies) {
        if(movies == null) return "Save Unsuccessful Data is Null";
        else{
            movieRepository.save(movies);
            return "Save successful";
        }
    }

    public String UpdateMovie(int id, Movie movies) {
        Movie myMovies = movieRepository.findById(id);
        if(myMovies != null) {
            myMovies.setName(movies.getName());
            movieRepository.save(myMovies);
            logger.info("Update : {}", myMovies);
            return "Update successful";
        }
        else return "Not Update : Data not available";
    }

    public String DeleteMovie(int id) {
        Movie myMovies = movieRepository.findById(id);
        if(myMovies != null) {
            movieRepository.deleteById(id);
            logger.info("Delete : Theater Id = {}", id);
            return "Delete successful";
        }
        else return "Not Delete : Data not available";
    }
}
