package myapp.ticket.Theater;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Theater() {
    }

    public Theater(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
