package myapp.ticket.Customer;

import myapp.ticket.Movie.Movie;
import myapp.ticket.Showtime.Showtime;
import myapp.ticket.Showtime.ShowtimeRepository;
import myapp.ticket.Theater.Theater;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    @Mock
    private ShowtimeRepository showtimeRepository = mock(ShowtimeRepository.class);

    List<Customer> customers = new ArrayList<>();

    List<Showtime> showtimes = new ArrayList<>();

    RestTemplate restTemplate = new RestTemplate();
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        Customer[] customer;
        Showtime[] showtime;
        try {
            ResponseEntity<Customer[]> response = restTemplate.getForEntity(new URL("http://localhost:8080/customer").toString(), Customer[].class);
            customer = response.getBody();

            ResponseEntity<Showtime[]> response1 = restTemplate.getForEntity(new URL("http://localhost:8080/showtime").toString(), Showtime[].class);
            showtime = response1.getBody();

        } catch (Exception e) {
            showtime = new Showtime[3];
            showtime[0] = new Showtime(1, new Theater(1, "THEATER-1"), new Movie(1, "MOVIE-1"), setDate(2022, 7, 13, 20));
            showtime[1] = new Showtime(2, new Theater(2, "THEATER-2"), new Movie(2, "MOVIE-2"), setDate(2022, 7, 13, 20));
            showtime[2] = new Showtime(3, new Theater(1, "THEATER-1"), new Movie(2, "MOVIE-1"), setDate(2022, 7, 13, 11));

            customer = new Customer[4];
            customer[0] = new Customer(1, "A", 1,showtime[0]);
            customer[1] = new Customer(2, "B", 1,showtime[0]);
            customer[2] = new Customer(3, "A", 5,showtime[1]);
            customer[3] = new Customer(4, "A", 10,showtime[2]);
        }

        assert customer != null;
        Collections.addAll(customers, customer);

        assert showtime != null;
        Collections.addAll(showtimes, showtime);
    }

    public Date setDate(int year, int month, int day, int hour){
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, year);
        myCal.set(Calendar.MONTH, month-1);
        myCal.set(Calendar.DAY_OF_MONTH, day);
        myCal.set(Calendar.HOUR_OF_DAY, hour);
        myCal.set(Calendar.MINUTE, 0);
        myCal.set(Calendar.SECOND, 0);
        return myCal.getTime();
    }

    @Test
    @DisplayName("Booking_Null")
    void addCustomer_Null() {
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);

        Assertions.assertEquals(customerService.AddCustomer(null), "!!ERROR!! Data is Null");
    }

    @Test
    @DisplayName("Booking_CheckRow")
    void addCustomer_CheckRow() {
        Customer customer1 = new Customer(customers.get(0).getId(),customers.get(0).getRow(),customers.get(0).getCol(),customers.get(0).getShowtime());
        customer1.setId(0);
        customer1.setRow("K");

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);

        Assertions.assertEquals(customerService.AddCustomer(customer1), "Row can only have A-J");
    }

    @Test
    @DisplayName("Booking_Duplicate")
    void addCustomer_Dup() {
        Customer customer1 = new Customer(customers.get(0).getId(),customers.get(0).getRow(),customers.get(0).getCol(),customers.get(0).getShowtime());

        List<Customer> Temp = new ArrayList<>();
        for(Customer temp:customers){
            if(temp.getShowtime() == customer1.getShowtime()){
                Temp.add(temp);
            }
        }

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);
        when(showtimeRepository.findById(customer1.getShowtime().getId())).thenReturn(showtimes.get(0));
        when(customerRepository.findAllByShowtimeIdOrderByRow(customer1.getShowtime().getId())).thenReturn(Temp);

        Assertions.assertEquals(customerService.AddCustomer(customer1), "Can't Book a chair unavailable");
    }

    @Test
    @DisplayName("Booking_CheckCol")
    void addCustomer_CheckCol() {
        Customer customer1 = new Customer(customers.get(0).getId(),customers.get(0).getRow(),customers.get(0).getCol(),customers.get(0).getShowtime());
        customer1.setId(0);
        customer1.setCol(20);

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);

        Assertions.assertEquals(customerService.AddCustomer(customer1), "Col can only have 1-10");
    }

    @Test
    @DisplayName("Booking_Bookingsuccessful")
    void addCustomer_Book() {
        Customer customer1 = new Customer(customers.get(0).getId(),customers.get(0).getRow(),customers.get(0).getCol(),customers.get(0).getShowtime());
        customer1.setId(10);
        customer1.setRow("C");
        customer1.setCol(5);

        List<Customer> Temp = new ArrayList<>();
        for(Customer temp:customers){
            if(temp.getShowtime() == customer1.getShowtime()){
                Temp.add(temp);
            }
        }

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);
        when(showtimeRepository.findById(customer1.getShowtime().getId())).thenReturn(showtimes.get(0));
        when(customerRepository.findAllByShowtimeIdOrderByRow(customer1.getShowtime().getId())).thenReturn(Temp);

        Assertions.assertEquals(customerService.AddCustomer(customer1), "Booking successful");
    }
}