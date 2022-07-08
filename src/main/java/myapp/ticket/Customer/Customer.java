package myapp.ticket.Customer;

import lombok.Data;
import myapp.ticket.Showtime.Showtime;

import javax.persistence.*;

@Entity
@Data
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String row;
    private int col;

    @ManyToOne
    private Showtime showtime;

    public Customer() {

    }

    public Customer(int id, String row, int col, Showtime showtime) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.showtime = showtime;
    }
}
