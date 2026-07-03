package in.tripva.assignment.booking.bookingController;


import in.tripva.assignment.booking.bookingDTO.BookingDTO;
import in.tripva.assignment.booking.bookingDTO.BookingResponseDTO;
import in.tripva.assignment.booking.bookingEntity.BookingEntity;
import in.tripva.assignment.booking.bookingService.BookingService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingDTO bookingDTO){

        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));



    }


}
