package myapp.ticket.Showtime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.beans.NamedArg;
import myapp.ticket.Movie.Movie;
import myapp.ticket.Theater.Theater;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShowtimeControllerTest {

    @InjectMocks
    private ShowtimeService showtimeService;

    @Mock
    private ShowtimeRepository showtimeRepository = mock(ShowtimeRepository.class);
    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter = objectMapper.writer();

    List<Showtime> showtimes = new ArrayList<>();

    Showtime showtime = new Showtime();

    public MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(showtimeService).build();

        ResponseEntity<Showtime[]> response = restTemplate.getForEntity(new URL("http://localhost:8080/showtime").toString(), Showtime[].class);
        Showtime[] showtime = response.getBody();


        assert showtime != null;
        Collections.addAll(showtimes, showtime);
    }

    public Date setDate(int year,int month,int day,int hour){
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
    @DisplayName("AddShowtime_DuplicateTimeAndTheater")
    void addShowtimeDup() {
        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);

        Showtime showtime1 = new Showtime(showtimes.get(0).getId(),showtimes.get(0).getTheater(),showtimes.get(0).getMovie(),showtimes.get(0).getDatetime());
        showtime1.setId(0);
        showtime1.setTheater(new Theater(1, null));
        showtime1.setMovie(new Movie(2, null));
        Assertions.assertEquals(showtimeService.AddShowtime(showtime1), "Duplicate Theater in same time");
    }

    @Test
    @DisplayName("AddShowtime_Save_successful")
    void addShowtimePass() {
        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Showtime showtime1 = new Showtime(showtimes.get(0).getId(),showtimes.get(0).getTheater(),showtimes.get(0).getMovie(),showtimes.get(0).getDatetime());
        showtime1.setId(0);
        showtime1.setTheater(new Theater(3, null));
        showtime1.setMovie(new Movie(1, null));


        showtime1.setDatetime(setDate(2022,7,10,11));
        Assertions.assertEquals(showtimeService.AddShowtime(showtime1), "Save successful");
    }
}