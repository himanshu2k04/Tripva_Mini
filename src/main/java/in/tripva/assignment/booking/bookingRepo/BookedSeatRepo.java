package in.tripva.assignment.booking.bookingRepo;

import in.tripva.assignment.booking.bookingEntity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedSeatRepo extends JpaRepository<BookedSeat,Long> {

    List<BookedSeat> findByBookingTripTripId(Long tripId);

    boolean existsByBookingTripTripIdAndSeatNumber(Long tripId,
                                                   String seatNumber);

    boolean existsByBookingTripTripId(Long tripId, String seat);
}
