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
    private Theater theater;

    @ManyToOne
    private Movie movie;


    private Date datetime;

    public Showtime() {
    }

    public Showtime(int id, Theater theater, Movie movie, Date datetime) {
        this.id = id;
        this.theater = theater;
        this.movie = movie;
        this.datetime = datetime;
    }
}
