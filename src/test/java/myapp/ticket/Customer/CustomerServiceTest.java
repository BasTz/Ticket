package myapp.ticket.Customer;

import myapp.ticket.Showtime.Showtime;
import myapp.ticket.Showtime.ShowtimeRepository;
import myapp.ticket.Showtime.ShowtimeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    List<Customer> customers = new ArrayList<>();

    Customer customer = new Customer();

    RestTemplate restTemplate = new RestTemplate();
    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        ResponseEntity<Customer[]> response = restTemplate.getForEntity(new URL("http://localhost:8080/customer").toString(), Customer[].class);
        Customer[] customer = response.getBody();


        assert customer != null;
        Collections.addAll(customers, customer);
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

        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);
        when(customerRepository.findAll()).thenReturn(customers);

        Assertions.assertEquals(customerService.AddCustomer(customer1), "Booking successful");
    }
}