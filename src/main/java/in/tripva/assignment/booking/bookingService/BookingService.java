package in.tripva.assignment.booking.bookingService;

import in.tripva.assignment.booking.bookingDTO.BookingDTO;
import in.tripva.assignment.booking.bookingDTO.BookingResponseDTO;
import in.tripva.assignment.booking.bookingEntity.BookedSeat;
import in.tripva.assignment.booking.bookingEntity.BookingEntity;
import in.tripva.assignment.booking.bookingEntity.Status;
import in.tripva.assignment.booking.bookingRepo.BookedSeatRepo;
import in.tripva.assignment.booking.bookingRepo.BookingRepo;
import in.tripva.assignment.exception.ResourceNotFoundException;
import in.tripva.assignment.trip.tripEntity.TripEntity;
import in.tripva.assignment.trip.tripRepo.TripRepo;
import in.tripva.assignment.user.userEntity.UserEntity;
import in.tripva.assignment.user.userRepo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class BookingService {

    private final BookingRepo bookingRepo;
    private final BookedSeatRepo bookedSeatRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final TripRepo tripRepo;

    @Transactional
    public BookingResponseDTO createBooking(BookingDTO bookingDTO) {

        UserEntity user = userRepo
                .findById(bookingDTO.getUserId()).orElseThrow(() ->
                        new ResourceNotFoundException("user Not Found"));

        TripEntity trip = tripRepo.findById(bookingDTO.getTripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        for(String seat : bookingDTO.getSeatNumbers()){

            if(bookedSeatRepo.existsByBookingTripTripIdAndSeatNumber(
                    trip.getTripId(),
                    seat)){

                throw new RuntimeException(seat + " already booked");
            }
        }
        BookingEntity booking = new BookingEntity();

        booking.setUser(user);
        booking.setTrip(trip);
        booking.setStatus(Status.CONFIRMED);
        booking.setCreatedAt(LocalDateTime.now());

        booking.setTotalAmount(
                trip.getFare() * bookingDTO.getSeatNumbers().size());

        BookingEntity savedBooking = bookingRepo.save(booking);

        List<BookedSeat> seats = new ArrayList<>();

        for(String seat : bookingDTO.getSeatNumbers()){

            BookedSeat bookedSeat = new BookedSeat();

            bookedSeat.setBooking(savedBooking);
            bookedSeat.setSeatNumber(seat);

            seats.add(bookedSeat);
        }

        bookedSeatRepo.saveAll(seats);

        savedBooking.setBookedSeats(seats);
        bookingRepo.save(savedBooking);

        return modelMapper.map(savedBooking,BookingResponseDTO.class);



    }
}
