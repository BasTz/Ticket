package myapp.ticket.Customer;

import myapp.ticket.Showtime.Showtime;
import myapp.ticket.Showtime.ShowtimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;


    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    public String AddCustomer(Customer customer) {
        if(customer == null){
            return "!!ERROR!! Data is Null";
        }
        else {
            boolean Flag = true;
            String[] checkRow = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
            for (String check : checkRow) {
                if (customer.getRow().equals(check)) {
                    Flag = true;
                    break;
                } else {
                    Flag = false;
                }
            }
            if (Flag) {
                for (int i = 1; i <= 10; i++) {
                    if (customer.getCol() == i) {
                        Flag = true;
                        break;
                    } else {
                        Flag = false;
                    }
                }
            } else {
                return "Row can only have A-J";
            }
            if (Flag) {
                List<Customer> checkDup = CustomerByShowtimeId(customer.getShowtime().getId());
                for (Customer check : checkDup) {
                    if (check.getRow().equals(customer.getRow()) && check.getCol() == customer.getCol()) {
                        return "Can't Book a chair unavailable";
                    }
                }
                customerRepository.save(customer);
                Showtime showtime = showtimeRepository.findById(customer.getShowtime().getId());
                //logger.info("Booking : {}",customer);
                logger.info("Booking At [ Seat = {}{} : Movie = {} : Theater = {} : Showtime = {} ]",customer.getRow(),customer.getCol(),
                        showtime.getMovie().getName(),showtime.getTheater().getName(),showtime.getDatetime());
                return "Booking successful";
            } else {
                return "Col can only have 1-10";
            }
        }
    }

    public String UpdateCustomer(int id, Customer customer) {
        Customer myCustomer = customerRepository.findById(id);
        if(customer == null){
            return "!!ERROR!! Data is Null";
        }
        else {
            boolean Flag = true;
            String[] checkRow = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
            for (String check : checkRow) {
                if (customer.getRow().equals(check)) {
                    Flag = true;
                    break;
                } else {
                    Flag = false;
                }
            }
            if (Flag) {
                for (int i = 1; i <= 10; i++) {
                    if (customer.getCol() == i) {
                        Flag = true;
                        break;
                    } else {
                        Flag = false;
                    }
                }
            } else {
                return "Row can only have A-J";
            }
            if (Flag) {
                List<Customer> checkDup = CustomerByShowtimeId(customer.getShowtime().getId());
                for (Customer check : checkDup) {
                    if (check.getRow().equals(customer.getRow()) && check.getCol() == customer.getCol()) {
                        return "Can't Book a chair unavailable";
                    }
                }

                myCustomer.setRow(customer.getRow());
                myCustomer.setCol(customer.getCol());
                myCustomer.setShowtime(customer.getShowtime());
                customerRepository.save(myCustomer);
                Customer customer1 = customerRepository.findById(customer.getId());
                logger.info("Update Booking : {}", customer1);
                return "Booking successful";
            } else {
                return "Col can only have 1-10";
            }
        }
    }

    public String DeleteCustomer(int id) {
        Customer myCustomer = customerRepository.findById(id);
        if(myCustomer != null){
            customerRepository.deleteById(id);
            logger.info("Cancel Booking At [ Seat = {}{} : Movie = {} : Theater = {} : Showtime = {} ]",myCustomer.getRow(),myCustomer.getCol(),
                    myCustomer.getShowtime().getMovie().getName(),myCustomer.getShowtime().getTheater().getName(),myCustomer.getShowtime().getDatetime());
            return "Delete successful";
        }
        else return "Not Delete : Data not available";
    }

    public List<Customer> CustomerByShowtimeId(int id) {
        return new ArrayList<>(customerRepository.findAllByShowtimeIdOrderByRow(id));
    }
}
