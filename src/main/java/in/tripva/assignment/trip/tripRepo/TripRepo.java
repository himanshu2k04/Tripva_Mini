package in.tripva.assignment.trip.tripRepo;

import in.tripva.assignment.trip.tripEntity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepo extends JpaRepository<TripEntity,Long> {

    List<TripEntity> findBySourceAndDestinationAndDate(
            String source,
            String destination,
            LocalDate date);



    Optional<TripEntity> findByBusBusId(Long busId);
}
