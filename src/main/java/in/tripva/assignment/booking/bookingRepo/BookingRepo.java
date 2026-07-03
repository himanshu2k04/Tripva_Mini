package in.tripva.assignment.booking.bookingRepo;

import in.tripva.assignment.booking.bookingEntity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity,Long> {

}
