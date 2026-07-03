package in.tripva.assignment.trip.tripRepo;

import in.tripva.assignment.trip.tripEntity.tripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface tripRepo extends JpaRepository<tripEntity,Long> {

    List<tripEntity> findBySourceAndDestinationAndDate(
            String source,
            String destination,
            LocalDate date);



    Optional<tripEntity> findByBusBusId(Long busId);
}
