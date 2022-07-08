package myapp.ticket.Showtime;

import lombok.Data;
import myapp.ticket.Movie.Movie;
import myapp.ticket.Theater.Theater;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Theater theator;

    @ManyToOne
    private Movie movie;


    private Date datatime;

    public Showtime() {
    }

    public Showtime(int id, Theater theator, Movie movie, Date datatime) {
        this.id = id;
        this.theator = theator;
        this.movie = movie;
        this.datatime = datatime;
    }
}
