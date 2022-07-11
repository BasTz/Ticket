package myapp.ticket.Customer;

import lombok.*;
import myapp.ticket.Showtime.Showtime;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String row;
    private int col;

    @ManyToOne
    private Showtime showtime;


    public Customer(int id, String row, int col, Showtime showtime) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.showtime = showtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) {
            Hibernate.getClass(this);
            Hibernate.getClass(o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
