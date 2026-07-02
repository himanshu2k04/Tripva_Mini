package in.tripva.assignment.bus.busRepo;


import in.tripva.assignment.bus.busEntity.busEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface busRepo extends JpaRepository<busEntity,Long> {
}
