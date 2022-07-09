package myapp.ticket.Showtime;

import lombok.*;
import myapp.ticket.Movie.Movie;
import myapp.ticket.Theater.Theater;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "Showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Theater theater;

    @ManyToOne
    private Movie movie;


    private Date datetime;

    public Showtime(int id, Theater theater, Movie movie, Date datetime) {
        this.id = id;
        this.theater = theater;
        this.movie = movie;
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Showtime showtime = (Showtime) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
