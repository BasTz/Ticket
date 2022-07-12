package myapp.ticket.Showtime;

import myapp.ticket.Movie.Movie;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShowtimeServiceTest {

    @InjectMocks
    private ShowtimeService showtimeService;

    @Mock
    private ShowtimeRepository showtimeRepository = mock(ShowtimeRepository.class);
    RestTemplate restTemplate = new RestTemplate();

    List<Showtime> showtimes = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

        Showtime[] showtime;
        try {
            ResponseEntity<Showtime[]> response = restTemplate.getForEntity(new URL("http://localhost:8080/showtime").toString(), Showtime[].class);
            showtime = response.getBody();
        }
        catch (Exception e){
            showtime = new Showtime[3];
            showtime[0] = new Showtime(1, new Theater(1, "THEATER-1"), new Movie(1, "MOVIE-1"), setDate(2022, 7, 13, 20));
            showtime[1] = new Showtime(2, new Theater(2, "THEATER-2"), new Movie(2, "MOVIE-2"), setDate(2022, 7, 13, 20));
            showtime[2] = new Showtime(3, new Theater(1, "THEATER-1"), new Movie(2, "MOVIE-1"), setDate(2022, 7, 13, 11));
        }

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
    //@Disabled
    @DisplayName("AddShowtime_DuplicateTimeAndTheater")
    void addShowtimeDup() {
        Showtime showtime1 = new Showtime(showtimes.get(0).getId(),showtimes.get(0).getTheater(),showtimes.get(0).getMovie(),showtimes.get(0).getDatetime());
        showtime1.setId(0);
        showtime1.setTheater(new Theater(1, null));
        showtime1.setMovie(new Movie(5, null));

        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);

        Assertions.assertEquals(showtimeService.AddShowtime(showtime1), "Duplicate Theater in same time");
    }

    @Test
    //@Disabled
    @DisplayName("AddShowtime_Save_successful")
    void addShowtimePass() {
        Showtime showtime1 = new Showtime(showtimes.get(0).getId(),showtimes.get(0).getTheater(),showtimes.get(0).getMovie(),showtimes.get(0).getDatetime());
        showtime1.setId(0);
        showtime1.setTheater(new Theater(3, null));
        showtime1.setMovie(new Movie(1, null));

        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        showtime1.setDatetime(setDate(2022,7,10,11));
        Assertions.assertEquals(showtimeService.AddShowtime(showtime1), "Save successful");
    }

    @Test
    //@Disabled
    @DisplayName("AddShowtime_NotInTime")
    void addShowtimeTime() {
        Showtime showtime1 = new Showtime(showtimes.get(0).getId(),showtimes.get(0).getTheater(),showtimes.get(0).getMovie(),showtimes.get(0).getDatetime());
        showtime1.setId(0);
        showtime1.setTheater(new Theater(1, null));
        showtime1.setMovie(new Movie(2, null));
        showtime1.setDatetime(setDate(2022,7,10,15));

        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);

        Assertions.assertEquals(showtimeService.AddShowtime(showtime1), "Every theater has a round 11:00 14:00 17:00 20:00 only");
    }

    @Test
    //@Disabled
    @DisplayName("AddShowtime_Null")
    void addShowtimenull() {
        when(showtimeRepository.save(Mockito.any(Showtime.class))).thenAnswer(i -> i.getArguments()[0]);
        when(showtimeRepository.findAll()).thenReturn(showtimes);

        Assertions.assertEquals(showtimeService.AddShowtime(null), "!!ERROR!! Data is Null");
    }
}