package myapp.ticket.Movie;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Movie() {
    }

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
